package dave.dev.ecommercestore.service;

import dave.dev.ecommercestore.dto.ProductDTO;
import dave.dev.ecommercestore.model.Product;
import dave.dev.ecommercestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategoryAndPrice(String category, double maxPrice) {
        return productRepository.findByCategoryAndPriceLessThan(category, maxPrice);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update the properties of the existing Product with values from the ProductDTO
        existingProduct.setName(productDTO.getName());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setPrice(productDTO.getPrice());

        // Save the updated product
        return productRepository.save(existingProduct);
    }


    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
