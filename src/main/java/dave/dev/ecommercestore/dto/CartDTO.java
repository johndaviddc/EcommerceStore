package dave.dev.ecommercestore.dto;

import java.util.List;

public class CartDTO {
    private Long id;
    private List<CartItemDTO> cartItems;

    public CartDTO(Long id, List<CartItemDTO> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
