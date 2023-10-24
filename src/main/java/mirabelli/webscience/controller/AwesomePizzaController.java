package mirabelli.webscience.controller;

import mirabelli.webscience.pojo.*;
import mirabelli.webscience.service.AwesomePizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/awesomePizza")
public class AwesomePizzaController {

    private final AwesomePizzaService awesomePizzaService;

    @Autowired
    public AwesomePizzaController(AwesomePizzaService awesomePizzaService) {
        this.awesomePizzaService = awesomePizzaService;
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody CreateOrderInput input) {
        String orderId = awesomePizzaService.createOrder(input);
        if (orderId != null) {
            return "Order created with ID: " + orderId;
        } else {
            return "Failed to create order";
        }
    }

    @GetMapping("/getOrderStatus")
    public String getOrderStatus(@RequestBody String id){
        String orderStatus = awesomePizzaService.getOrderStatus(id);
        if(orderStatus != null && !orderStatus.isEmpty()){
            return "Your order is in status :" + orderStatus;
        } else{
            return "Error retrieving your order with id: " + id;
        }
    }

    @GetMapping("/getOrder")
    public List<String> getOrder(@RequestBody String pizzaChefCode){
        GetOrderOutput order = awesomePizzaService.getOrder(pizzaChefCode);
        return order.getPizzas();
    }

    @PostMapping("/updateOrderStatus")
    public boolean updateOrderStatus(@RequestBody UpdateOrderStatusInput input){
        return awesomePizzaService.updateOrderStatus(input);
    }

    @PostMapping("/addPizza")
    public String addPizza(@RequestBody AddPizzaInput input){
        boolean result = awesomePizzaService.addPizza(input);
        if(result){
            return "Added new pizza: " + input.getName();
        } else {
            return "Error adding pizza";
        }
    }

    @PostMapping("/removePizza")
    public String removePizza(@RequestBody RemovePizzaInput input){
        boolean result = awesomePizzaService.removePizza(input);
        if(result){
            return "Pizza removed";
        } else {
            return "Error removing pizza";
        }
    }

    @GetMapping("/getMenu")
    public GetMenuOutput getMenu(){
        return awesomePizzaService.getMenu();
    }
}


