package dave.dev.ecommercestore.service;

import dave.dev.ecommercestore.model.Cart;
import dave.dev.ecommercestore.model.CartItem;
import dave.dev.ecommercestore.model.Product;
import dave.dev.ecommercestore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart addItemToCart(Long cartId, Long productId, int quantity) {
        // Retrieve the cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Retrieve the product
        Product product = productService.getProductById(productId);

        // Check if the product exists in the cart
        CartItem existingItem = cart.findCartItemByProductId(productId);

        if (existingItem != null) {
            // Product exists in the cart, update the quantity
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Product does not exist in the cart, create a cart item
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        // Recalculate cart total
        cart.calculateTotal();

        // Save or update the cart
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long cartId, Long productId) {
        // Retrieve the cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Find the cart item for the specified product and remove it
        CartItem itemToRemove = cart.findCartItemByProductId(productId);

        if (itemToRemove != null) {
            cart.getItems().remove(itemToRemove);
            // Recalculate cart total
            cart.calculateTotal();
            // Save or update the cart
            return cartRepository.save(cart);
        } else {
            throw new RuntimeException("Product not found in cart");
        }
    }

    public void deleteCart(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cart not found");
        }
    }
}