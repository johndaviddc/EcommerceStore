package dave.dev.ecommercestore.repository;

import dave.dev.ecommercestore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserIdAndStatus(Long userId, String status);
}
