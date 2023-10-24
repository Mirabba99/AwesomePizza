package mirabelli.webscience.pojo;

import java.io.Serializable;

public class RemovePizzaInput implements Serializable {

    private static final long serialVersionUID = -3642428102371196393L;

    private String pizzaId;

    private String pizzaChefCode;

    public RemovePizzaInput() {
    }

    public RemovePizzaInput(String pizzaId, String pizzaChefCode) {
        this.pizzaId = pizzaId;
        this.pizzaChefCode = pizzaChefCode;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaChefCode() {
        return pizzaChefCode;
    }

    public void setPizzaChefCode(String pizzaChefCode) {
        this.pizzaChefCode = pizzaChefCode;
    }

}
