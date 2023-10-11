package dave.dev.ecommercestore.dto;

import java.util.List;

public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> orderItems;
    private String someProperty;
    private Long productId;
    private int quantity;


    public OrderDTO(Long id, List<OrderItemDTO> orderItems, String someProperty, Long productId, int quantity) {
        this.id = id;
        this.orderItems = orderItems;
        this.someProperty = someProperty;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getSomeProperty() {
        return someProperty;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}