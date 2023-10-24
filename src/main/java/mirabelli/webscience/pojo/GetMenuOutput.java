package mirabelli.webscience.pojo;

import java.io.Serializable;
import java.util.List;

public class GetMenuOutput implements Serializable {

    private static final long serialVersionUID = -3675998415586370788L;

    private List<String> pizzas;

    private String errorMessage;

    public GetMenuOutput() {
    }

    public GetMenuOutput(List<String> pizzas, String errorMessage) {
        this.pizzas = pizzas;
        this.errorMessage = errorMessage;
    }

    public List<String> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<String> pizzas) {
        this.pizzas = pizzas;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}

