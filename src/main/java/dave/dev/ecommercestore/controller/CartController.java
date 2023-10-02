package dave.dev.ecommercestore.controller;

import dave.dev.ecommercestore.dto.CartDTO;
import dave.dev.ecommercestore.model.Cart;
import dave.dev.ecommercestore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart createdCart = cartService.createCart();
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}/addItem")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long cartId, @RequestBody CartDTO cartDTO) {
        Cart updatedCart = cartService.addItemToCart(cartId, cartDTO.getProductId(), cartDTO.getQuantity());
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/removeItem/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart updatedCart = cartService.removeItemFromCart(cartId, productId);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
