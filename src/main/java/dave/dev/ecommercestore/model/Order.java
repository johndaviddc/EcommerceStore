package dave.dev.ecommercestore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private double totalAmount; // Add totalAmount property
    private String status; // Add status property
    private String someProperty;

    public Order(Long id, List<OrderItem> orderItems, String someProperty) {
        this.id = id;
        this.orderItems = orderItems;
        this.someProperty = someProperty;
    }

    public Order() {

    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderItem findOrderItemByProductId(Long productId) {
        for (OrderItem item : orderItems) {
            if (item.getProduct().getId().equals(productId)) {
                return item; // Found the order item with the specified product ID
            }
        }
        return null;
    }

    public void calculateTotal() {
        double totalAmount = 0.0;

        // Iterate over the order items and calculate the total amount
        for (OrderItem item : orderItems) {
            double itemTotal = item.getQuantity() * item.getProduct().getPrice();
            totalAmount += itemTotal;
        }

        this.totalAmount = totalAmount; // Set the total amount property
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSomeProperty() {
        return someProperty;
    }

    public void setSomeProperty(String someProperty) {
        this.someProperty = someProperty;
    }
}