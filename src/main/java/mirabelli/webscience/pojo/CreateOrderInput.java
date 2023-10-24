package mirabelli.webscience.pojo;

import java.io.Serializable;
import java.util.List;

public class CreateOrderInput implements Serializable {

    private static final long serialVersionUID = 4228042954023789544L;

    private List<String> pizzas;

    public CreateOrderInput() {
    }

    public CreateOrderInput(List<String> pizzas) {
        this.pizzas = pizzas;
    }

    public List<String> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<String> pizzas) {
        this.pizzas = pizzas;
    }

}
