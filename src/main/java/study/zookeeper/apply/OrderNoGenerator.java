package study.zookeeper.apply;

/**
 * 订单号生成器
 */
public class OrderNoGenerator {

    private static int i=0;

    public String generateOrderNo() {
//        return System.currentTimeMillis() + " " + ++i;
        return String.valueOf(++i);
    }

}
