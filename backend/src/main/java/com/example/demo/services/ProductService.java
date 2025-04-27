package com.example.demo.services;

import com.example.demo.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product createProduct(String product, MultipartFile[] images) throws IOException;
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
    byte[] getImageByName(String imageName) throws IOException;



    Product getProductByName(String productName);
}
