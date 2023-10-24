package mirabelli.webscience.pojo;

import java.io.Serializable;

public class AddPizzaInput implements Serializable {

    private static final long serialVersionUID = -9169577846646381460L;

    private String name;

    private String ingredients;

    private String pizzaChefCode;

    public AddPizzaInput() {
    }

    public AddPizzaInput(String name, String ingredients, String pizzaChefCode) {
        this.name = name;
        this.ingredients = ingredients;
        this.pizzaChefCode = pizzaChefCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPizzaChefCode() {
        return pizzaChefCode;
    }

    public void setPizzaChefCode(String pizzaChefCode) {
        this.pizzaChefCode = pizzaChefCode;
    }

}
