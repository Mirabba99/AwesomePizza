package mirabelli.webscience.pojo;

import java.io.Serializable;
import java.util.List;

public class GetOrderOutput implements Serializable {

    private static final long serialVersionUID = -3665658087154720942L;

    private List<String> pizzas;

    public GetOrderOutput() {
    }

    public GetOrderOutput(List<String> pizzas) {
        this.pizzas = pizzas;
    }

    public List<String> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<String> pizzas) {
        this.pizzas = pizzas;
    }

}
