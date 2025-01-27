package com.example.demo.serviceImpl;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setBasePrice(updatedProduct.getBasePrice());
                    product.setStockQuantity(updatedProduct.getStockQuantity());
                    product.setCategory(updatedProduct.getCategory());
                    product.setStore(updatedProduct.getStore());
                    product.setUpdatedAt(updatedProduct.getUpdatedAt());
                    return productRepository.save(product);
                }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
}
