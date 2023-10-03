package dave.dev.ecommercestore.service;

import dave.dev.ecommercestore.dto.OrderDTO;
import dave.dev.ecommercestore.model.Order;
import dave.dev.ecommercestore.model.OrderItem;
import dave.dev.ecommercestore.model.Product;
import dave.dev.ecommercestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        // Set properties of the Order object based on the OrderDTO
        order.setSomeProperty(orderDTO.getSomeProperty());
        // Save the order
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, OrderDTO orderDTO) {
        // Retrieve the existing order from the database
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Apply changes from the OrderDTO to the existing order
        // For example, if your OrderDTO has properties like orderItems and totalAmount,
        // you can update them like this:

        // Update order items if needed
        // existingOrder.setOrderItems(orderDTO.getOrderItems());

        // Update total amount if needed
        // existingOrder.setTotalAmount(orderDTO.getTotalAmount());

        // Save the updated order back to the database
        Order updatedOrder = orderRepository.save(existingOrder);

        return updatedOrder;
    }

    public void cancelOrder(Long id) {
        // Retrieve the existing order from the database
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Check if the order is in a cancelable state (e.g., it's not already canceled)
        if (!existingOrder.getStatus().equals("Canceled")) {
            // Update the status of the order to "Canceled"
            existingOrder.setStatus("Canceled");

            // Save the updated order back to the database
            orderRepository.save(existingOrder);
        } else {
            // Handle the case where the order is already canceled
            throw new RuntimeException("Order is already canceled");
        }
    }


    public Order addOrderItem(Long orderId, Long productId, int quantity) {
        // Retrieve the order
        Order order = getOrderById(orderId);

        // Retrieve the product
        Product product = productService.getProductById(productId);

        // Check id the product exists in the order
        OrderItem existingItem = order.findOrderItemByProductId(productId);

        if (existingItem != null) {
            // Product exists in the order, update the quantity
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Product does not exist in the order, create a new order item
            OrderItem newItem = new OrderItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setOrder(order);
            order.getOrderItems().add(newItem);
        }

        // Recalculate order total
        order.calculateTotal();

        // Save or update the order
        return orderRepository.save(order);
    }

    public Order removeOrderItem(Long orderId, Long productId) {
        // Retrieve the order
        Order order = getOrderById(orderId);

        // Find the order item for the specified product and remove it
        OrderItem itemToRemove = order.findOrderItemByProduct(productId);

        if (itemToRemove != null) {
            order.getOrderItems().remove(itemToRemove);
            // Recalculate order total
            order.calculateTotal();
            // Save or update the order
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Product not found in order");
        }
    }
}
