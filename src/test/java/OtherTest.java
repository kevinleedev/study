import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import sun.net.www.http.HttpClient;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/10/21 16:20
 **/
public class OtherTest {

    public static void main(String[] args) throws Exception {
        postData();
    }

    public static void postData() throws Exception {
        String url = "http://dev.service.wumart.com/pushTerminalData";
//        String contentTypeSuffix = ";accountName=dmall;password=dmall-admin;gatewayToken=XNvo4TUWeoYM6WbXMLfc%2FA%3D%3D;";
//        HttpEntity stringEntity = new StringEntity(data);
//        Header[] headers = new Header[1];
//        Header header = new BasicHeader("Content-Type", "application/json;accountName=dmall;password=dmall-admin;gatewayToken=MWNE2GglTfE%2B0ppZavyM1w%3D%3D;");
//        headers[0] = header;
//        String post = HttpClientUtils.post(url, JSONObject.parseObject(data).toJSONString());
        String get = HttpClientUtils.get("http://www.baidu.com");
        System.out.println(get);
    }

    private static final String data = "{\n" +
            "  \"dataJson\": [\n" +
            "    {\n" +
            "      \"fTime\": \"20171021160434728_DMHH018058\",\n" +
            "      \"humanId\": \"-9\",\n" +
            "      \"memberCardType\": \"0\",\n" +
            "      \"memberChannel\": \"D1\",\n" +
            "      \"memberScore\": \"0\",\n" +
            "      \"orderId\": \"367444170\",\n" +
            "      \"orgNO\": \"1019\",\n" +
            "      \"payments\": [\n" +
            "        {\n" +
            "          \"invAmt\": 0,\n" +
            "          \"itemId\": 10,\n" +
            "          \"paymentAmt\": 4,\n" +
            "          \"paymentNum\": \"1000024705000000006\",\n" +
            "          \"psId\": \"90\",\n" +
            "          \"remainAmt\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"invAmt\": 0,\n" +
            "          \"itemId\": 20,\n" +
            "          \"paymentAmt\": 2.04,\n" +
            "          \"paymentNum\": \"1000024704000000012\",\n" +
            "          \"psId\": \"90\",\n" +
            "          \"remainAmt\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"invAmt\": 0,\n" +
            "          \"itemId\": 30,\n" +
            "          \"paymentAmt\": 1,\n" +
            "          \"paymentNum\": \"1000024703000000004\",\n" +
            "          \"psId\": \"90\",\n" +
            "          \"remainAmt\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"invAmt\": 0,\n" +
            "          \"itemId\": 40,\n" +
            "          \"paymentAmt\": 1,\n" +
            "          \"paymentNum\": \"插件大事\",\n" +
            "          \"psId\": \"57\",\n" +
            "          \"remainAmt\": 9487.5\n" +
            "        }\n" +
            "      ],\n" +
            "      \"posId\": \"230\",\n" +
            "      \"saleDt\": \"2017-10-21 10:43:59\",\n" +
            "      \"saleId\": \"38\",\n" +
            "      \"saleItems\": [\n" +
            "        {\n" +
            "          \"couponAmt\": 7.97,\n" +
            "          \"couponNO\": \"1000024705000000006,1000024704000000012,1000024703000000004,1000024701000000004\",\n" +
            "          \"couponTempNO\": \"\",\n" +
            "          \"disSaleAmt\": 0,\n" +
            "          \"itemId\": 10,\n" +
            "          \"itemText\": \"\",\n" +
            "          \"needMemScore\": 0,\n" +
            "          \"newSaleAmt\": 9,\n" +
            "          \"pluType\": \"5\",\n" +
            "          \"promoID\": \"\",\n" +
            "          \"rPromoID\": \"0\",\n" +
            "          \"retailPrice\": 4.5,\n" +
            "          \"saleAmt\": 9,\n" +
            "          \"saleCount\": 2,\n" +
            "          \"saleQty\": 2,\n" +
            "          \"scanPlu\": \"110212\",\n" +
            "          \"sku\": \"110212\",\n" +
            "          \"userNo\": \"2670\",\n" +
            "          \"uuId\": \"c7f76cf7-49e7-408a-8442-a650efab66fb\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"couponAmt\": 0.36,\n" +
            "          \"couponNO\": \"1000024705000000006,1000024704000000012,1000024703000000004,1000024701000000004\",\n" +
            "          \"couponTempNO\": \"\",\n" +
            "          \"disSaleAmt\": 0,\n" +
            "          \"itemId\": 20,\n" +
            "          \"itemText\": \"\",\n" +
            "          \"needMemScore\": 0,\n" +
            "          \"newSaleAmt\": 0.4,\n" +
            "          \"pluType\": \"5\",\n" +
            "          \"promoID\": \"\",\n" +
            "          \"rPromoID\": \"0\",\n" +
            "          \"retailPrice\": 0.2,\n" +
            "          \"saleAmt\": 0.4,\n" +
            "          \"saleCount\": 2,\n" +
            "          \"saleQty\": 2,\n" +
            "          \"scanPlu\": \"256641\",\n" +
            "          \"sku\": \"256641\",\n" +
            "          \"userNo\": \"2670\",\n" +
            "          \"uuId\": \"1be4e41b-e1cc-4253-aa08-335578f637da\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"couponAmt\": 0.71,\n" +
            "          \"couponNO\": \"1000024705000000006,1000024704000000012,1000024703000000004,1000024701000000004\",\n" +
            "          \"couponTempNO\": \"\",\n" +
            "          \"disSaleAmt\": 0,\n" +
            "          \"itemId\": 30,\n" +
            "          \"itemText\": \"\",\n" +
            "          \"needMemScore\": 0,\n" +
            "          \"newSaleAmt\": 0.8,\n" +
            "          \"pluType\": \"5\",\n" +
            "          \"promoID\": \"\",\n" +
            "          \"rPromoID\": \"0\",\n" +
            "          \"retailPrice\": 0.4,\n" +
            "          \"saleAmt\": 0.8,\n" +
            "          \"saleCount\": 2,\n" +
            "          \"saleQty\": 2,\n" +
            "          \"scanPlu\": \"256506\",\n" +
            "          \"sku\": \"256506\",\n" +
            "          \"userNo\": \"2670\",\n" +
            "          \"uuId\": \"b500dc42-d08f-4b85-b44a-f4412adca7bb\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"taskId\": \"9\",\n" +
            "      \"userNo\": \"2670\",\n" +
            "      \"uuId\": \"4bd8af47-a2cb-4988-9c52-272654a3ab58\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"orgNo\": \"1019\",\n" +
            "  \"userCode\": \"DMHH018058\",\n" +
            "  \"uuid\": \"21089a0a-eaa7-4b56-bac5-663eb4cae916\"\n" +
            "}";

}
