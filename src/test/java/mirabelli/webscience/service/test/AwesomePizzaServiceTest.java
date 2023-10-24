package mirabelli.webscience.service.test;

import mirabelli.webscience.persistence.Order;
import mirabelli.webscience.persistence.OrderRepository;
import mirabelli.webscience.persistence.Pizza;
import mirabelli.webscience.persistence.PizzaRepository;
import mirabelli.webscience.pojo.*;
import mirabelli.webscience.service.AwesomePizzaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AwesomePizzaService.class)
public class AwesomePizzaServiceTest {

    @Autowired
    private AwesomePizzaService awesomePizzaBean;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private PizzaRepository pizzaRepository;

    @Test
    public void testAllMethods() {
        Pizza margheritaPizza = new Pizza();
        margheritaPizza.setName("Margherita");
        margheritaPizza.setIngredients("Tomato, Mozzarella, Oil, Basil");

        Pizza marinaraPizza = new Pizza();
        marinaraPizza.setName("Marinara");
        marinaraPizza.setIngredients("Tomato, Oregano, Garlic");

        Pizza carbonaraPizza = new Pizza();
        carbonaraPizza.setId(3L);
        carbonaraPizza.setName("Carbonara");
        carbonaraPizza.setIngredients("egg, pepper, bacon, pecorino");

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setOrderStatus(OrderStatus.ORDERED.getOrderStatus());
        savedOrder.setPizzas(List.of(margheritaPizza, marinaraPizza));
        savedOrder.setCreatedAt(new Date(System.currentTimeMillis()));

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
        when(pizzaRepository.findByName("Marinara")).thenReturn(marinaraPizza);
        when(pizzaRepository.findByName("Margherita")).thenReturn(margheritaPizza);
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(carbonaraPizza);
        when(orderRepository.findOldestOrderByStatus(OrderStatus.ORDERED.getOrderStatus())).thenReturn(savedOrder);
        when(orderRepository.findById(savedOrder.getId())).thenReturn(Optional.of(savedOrder));

        CreateOrderInput input = new CreateOrderInput();
        input.setPizzas(List.of("Margherita", "Marinara"));
        String orderId = awesomePizzaBean.createOrder(input);
        assertNotNull(orderId);

        when(orderRepository.findOrderStatusById(Long.valueOf(orderId))).thenReturn(OrderStatus.ORDERED.getOrderStatus());
        String orderStatus = awesomePizzaBean.getOrderStatus(orderId);
        assertEquals(OrderStatus.ORDERED.getOrderStatus(), orderStatus);

        String correctPizzaChefCode = "Aw3som3P!zz4";
        GetOrderOutput correctGetOutputOrder = awesomePizzaBean.getOrder(correctPizzaChefCode);
        assertEquals(savedOrder.getPizzas().size(), correctGetOutputOrder.getPizzas().size());

        GetOrderOutput wrongCodeGetOutPutOrder = awesomePizzaBean.getOrder("testTest");
        assertNull(wrongCodeGetOutPutOrder.getPizzas());

        when(orderRepository.existsByOrderStatus(OrderStatus.PROCESSING.getOrderStatus())).thenReturn(true);
        GetOrderOutput stillOnGoingGetOutPutOrder = awesomePizzaBean.getOrder(correctPizzaChefCode);
        assertNull(stillOnGoingGetOutPutOrder.getPizzas());

        UpdateOrderStatusInput updateOrderStatusInput = new UpdateOrderStatusInput(orderId, OrderStatus.COMPLETED.getOrderStatus(), correctPizzaChefCode);
        boolean changeOrderResult = awesomePizzaBean.updateOrderStatus(updateOrderStatusInput);
        assertTrue(changeOrderResult);

        AddPizzaInput addPizzaInput = new AddPizzaInput("Carbonara", "egg, pepper, bacon, pecorino", correctPizzaChefCode);
        boolean addPizzaResult = awesomePizzaBean.addPizza(addPizzaInput);
        assertTrue(addPizzaResult);

        RemovePizzaInput removePizzaInput = new RemovePizzaInput("3", correctPizzaChefCode);
        boolean removePizzaResult = awesomePizzaBean.removePizza(removePizzaInput);
        assertTrue(removePizzaResult);

        when(pizzaRepository.findAll()).thenReturn(List.of(margheritaPizza, marinaraPizza, carbonaraPizza));
        GetMenuOutput getMenuOutputCorrect = awesomePizzaBean.getMenu();
        assertNull(getMenuOutputCorrect.getErrorMessage());

        when(pizzaRepository.findAll()).thenReturn(null);
        GetMenuOutput getMenuOutputWithError = awesomePizzaBean.getMenu();
        assertNotNull(getMenuOutputWithError.getErrorMessage());
    }
}
