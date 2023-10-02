package dave.dev.ecommercestore.controller;

import dave.dev.ecommercestore.dto.OrderDTO;
import dave.dev.ecommercestore.model.Order;
import dave.dev.ecommercestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Order createdOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/addItem")
    public ResponseEntity<Order> addOrderItem(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = orderService.addOrderItem(orderId, orderDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/removeItem/{productId}")
    public ResponseEntity<Order> removeOrderItem(@PathVariable Long orderId, @PathVariable Long productId) {
        Order updatedOrder = orderService.removeOrderItem(orderId, productId);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updatedOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
