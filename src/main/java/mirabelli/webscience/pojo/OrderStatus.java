package mirabelli.webscience.pojo;

public enum OrderStatus {

    ORDERED("ordered"),
    PROCESSING("processing"),
    DELETED("deleted"),
    COMPLETED("completed");

    private String orderStatus;
    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

}
