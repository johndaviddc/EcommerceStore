package dave.dev.ecommercestore.dto;


public class CartDTO {
    private Long productId;
    private int quantity;

    // Getter and setter methods for productId and quantity
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
