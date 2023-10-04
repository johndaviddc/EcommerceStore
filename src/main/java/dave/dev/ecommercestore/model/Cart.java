package dave.dev.ecommercestore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;
    private double totalAmount;

    public Cart(Long id, List<CartItem> cartItems) {
        this.id = id;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return items;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.items = cartItems;
    }

    public Cart() {

    }
    public CartItem findCartItemByProductId(Long productId) {
        // Implement the logic to find a cart item by product ID
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                return item; // Found the cart item with the specified product ID
            }
        }
        return null; // Product not found in the cart
    }

    public void calculateTotal() {
        double total = 0.0;

        // Calculate the total amount based on the cart items
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }

        // Set the total amount property of the cart
        this.totalAmount = total;
    }


}
