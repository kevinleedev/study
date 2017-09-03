package study.dp.builder;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private String orderNo;
    private String orderStatus;
    private Date orderDate;
    private Long orderUserId;

    private static class OrderBuilder {
        private String orderNo;
        private String orderStatus;
        private Date orderDate;
        private Long orderUserId;

        public OrderBuilder(String orderNo) {
            this.orderNo = orderNo;
        }

        public OrderBuilder orderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderBuilder orderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public OrderBuilder orderUserId(Long orderUserId) {
            this.orderUserId = orderUserId;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

    }

    private Order(OrderBuilder builder) {
        this.orderNo = builder.orderNo;
        this.orderStatus = builder.orderStatus;
        this.orderDate = builder.orderDate;
        this.orderUserId = builder.orderUserId;
    }

    public static void main(String[] args) {
        Order order = new OrderBuilder("1").orderDate(new Date()).orderStatus("finished").orderUserId(123L).build();
        System.out.println(order.orderStatus);
    }

}
