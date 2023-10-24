package mirabelli.webscience.pojo;

import java.io.Serializable;

public class UpdateOrderStatusInput implements Serializable {

    private static final long serialVersionUID = -5503191975540964808L;

    private String orderId;

    private String orderStatus;

    private String pizzaChefCode;

    public UpdateOrderStatusInput() {
    }

    public UpdateOrderStatusInput(String orderId,String orderStatus, String pizzaChefCode) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.pizzaChefCode = pizzaChefCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPizzaChefCode() {
        return pizzaChefCode;
    }

    public void setPizzaChefCode(String pizzaChefCode) {
        this.pizzaChefCode = pizzaChefCode;
    }

}
