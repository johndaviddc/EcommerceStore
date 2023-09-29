package dave.dev.ecommercestore.dto;

import java.util.List;

public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> orderItems;

    public OrderDTO(Long id, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
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
}
