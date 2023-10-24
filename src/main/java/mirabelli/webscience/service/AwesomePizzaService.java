package mirabelli.webscience.service;

import mirabelli.webscience.persistence.Order;
import mirabelli.webscience.persistence.OrderRepository;
import mirabelli.webscience.persistence.Pizza;
import mirabelli.webscience.persistence.PizzaRepository;
import mirabelli.webscience.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AwesomePizzaService {

    private static final Logger logger = LoggerFactory.getLogger(AwesomePizzaService.class);


    private final OrderRepository orderRepository;

    private final PizzaRepository pizzaRepository;

    private final String pizzaChefAuthorizedCode = "Aw3som3P!zz4";

    @Autowired
    public AwesomePizzaService(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public String createOrder(CreateOrderInput input) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.ORDERED.getOrderStatus());
        List<Pizza> pizzas = new ArrayList<>();
        try {
            pizzas = getPizzas(input);
        } catch (Exception e) {
            return null;
        }
        order.setPizzas(pizzas);
        Order completOrder = orderRepository.save(order);
        return String.valueOf(completOrder.getId());
    }

    public String getOrderStatus(String orderId){
        if(isNotNullOrEmpty(orderId)){
            return orderRepository.findOrderStatusById(Long.parseLong(orderId));
        } else{
            return "";
        }
    }

    public GetOrderOutput getOrder(String pizzaChefCode){
        GetOrderOutput output = new GetOrderOutput();
        if(checkPizzaChefCodeIsAuthorized(pizzaChefCode) && !checkOnGoingOrder()){
            Order order = orderRepository.findOldestOrderByStatus(OrderStatus.ORDERED.getOrderStatus());
            List<String> pizzas = new ArrayList<>();
            for (Pizza pizza : order.getPizzas()) {
                pizzas.add(pizza.getName());
            }
            output.setPizzas(pizzas);
            updateOrderStatusById(order.getId(), OrderStatus.PROCESSING.getOrderStatus());
        } else{
            logger.error("Still an pending order please close last order before get a new one");
        }
        return output;
    }

    public boolean updateOrderStatus(UpdateOrderStatusInput input){
        boolean result = false;
        if(checkPizzaChefCodeIsAuthorized(input.getPizzaChefCode())){
            result = updateOrderStatusById(Long.valueOf(input.getOrderId()), input.getOrderStatus());
        }
        return result;
    }

    public boolean addPizza(AddPizzaInput input){
        if(checkPizzaChefCodeIsAuthorized(input.getPizzaChefCode()) && isNotNullOrEmpty(input.getName()) && isNotNullOrEmpty(input.getIngredients())){
            Pizza pizza = new Pizza(input.getName(), input.getIngredients());
            Pizza savedPizza = pizzaRepository.save(pizza);
            return savedPizza.getId() != null;
        } else {
            return false;
        }
    }

    public boolean removePizza(RemovePizzaInput input){
        if(checkPizzaChefCodeIsAuthorized(input.getPizzaChefCode()) && isNotNullOrEmpty(input.getPizzaId())){
            pizzaRepository.deleteById(Long.parseLong(input.getPizzaId()));
            return true;
        } else {
            return false;
        }
    }

    public GetMenuOutput getMenu() {
        GetMenuOutput output = new GetMenuOutput();
        List<Pizza> menu = pizzaRepository.findAll();
        if(menu == null || menu.isEmpty()){
           output.setErrorMessage("Error retrieving menu");
        }
        return output;
    }

    private List<Pizza> getPizzas(CreateOrderInput input) throws Exception {
        List<Pizza> toOrderPizzas = new ArrayList<>();
        List<String> pizzaNames = input.getPizzas();
        for (String pizzaName : pizzaNames) {
            Pizza pizza = pizzaRepository.findByName(pizzaName);
            if (pizza != null) {
                toOrderPizzas.add(pizza);
            } else {
                logger.error("No pizza in the menu with name= " + pizzaName);
                throw new Exception();
            }
        }
        return toOrderPizzas;
    }

    private boolean updateOrderStatusById(Long orderId, String newOrderStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            order.setOrderStatus(newOrderStatus);
            orderRepository.save(order);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkOnGoingOrder() {
        return orderRepository.existsByOrderStatus(OrderStatus.PROCESSING.getOrderStatus());
    }

    private boolean checkPizzaChefCodeIsAuthorized(String pizzaChefCode) {
        return pizzaChefCode != null && pizzaChefCode.equals(pizzaChefAuthorizedCode);
    }

    public boolean isNotNullOrEmpty(String parameter){
        return parameter != null && !parameter.isEmpty();
    }

}
