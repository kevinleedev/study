import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 描述：<br/>
 *
 * @author lilin
 * @version v1.0.0
 * @see
 * @since 2017/2/23 16:17
 */
public class HttpClientUtils {

    private static HttpClientInstance instance = SpringContextUtils.getBean(HttpClientInstance.class);

    public static String get(String url) throws Exception {
        return instance.get(url);
    }

    public static String get(String url, int socketTimeout) throws Exception {
        return instance.get(url, socketTimeout);
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        return instance.post(url, params);
    }

    public static String post(String url, Map<String, String> params, int socketTimeout) throws Exception {
        return instance.post(url, params, socketTimeout);
    }

    public static String post(String url, String json) throws Exception {
        return instance.post(url, json);
    }

    public static String post(String url, String name, File file) throws Exception {
        return instance.post(url, name, file);
    }

    public static String post(String url, HttpEntity body, Header... headers) throws Exception {
        return instance.post(url, body, headers);
    }

}

/**
 * <pre>
 * 实现描述：http访问客户端工具类的核心类，不用直接使用
 * spring容器管理的用意是通过切面，统一记录交互日志（详见 ExchangeLogAspect）
 * </pre>
 *
 * @author simon
 * @version v1.0.0
 * @see
 * @since 16/8/7 下午5:44
 */
@Component
class HttpClientInstance {

    private static final int CONNECT_TIMEOUT = 5000;

    private static final int SOCKET_TIMEOUT = 10000;

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public String get(String url) throws Exception {
        return execute(Request.Get(url));
    }

    public String get(String url, int socketTimeout) throws Exception {
        return execute(Request.Get(url), socketTimeout);
    }

    public String post(String url, Map<String, String> params) throws Exception {
        return execute(Request.Post(url).bodyForm(convertMapToForm(params).build(), UTF_8));
    }

    public String post(String url, Map<String, String> params, int socketTimeout) throws Exception {
        return execute(Request.Post(url).bodyForm(convertMapToForm(params).build(), UTF_8), socketTimeout);
    }

    public String post(String url, String json) throws Exception {
        return execute(Request.Post(url).bodyString(json, ContentType.APPLICATION_JSON));
    }

    public String post(String url, String name, File file) throws Exception {
        HttpEntity body = MultipartEntityBuilder.create().addBinaryBody(name, file).build();
        return execute(Request.Post(url).body(body));
    }

    public String post(String url, HttpEntity body, Header... headers) throws Exception {
        return execute(Request.Post(url).setHeaders(headers).body(body));
    }

    private String execute(Request request) throws Exception {
        return execute(request, SOCKET_TIMEOUT);
    }

    private String execute(Request request, int socketTimeout) throws Exception {
        Content content = request.connectTimeout(CONNECT_TIMEOUT).socketTimeout(socketTimeout).execute()
                .returnContent();
        Charset charset = content.getType().getCharset();
        return new String(content.asBytes(), charset != null ? charset : UTF_8);
    }

    private Form convertMapToForm(Map<String, String> params) {
        Form form = Form.form();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            form.add(entry.getKey(), entry.getValue());
        }
        return form;
    }
}
