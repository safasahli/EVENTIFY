package com.example.demo.serviceImpl;

import com.example.demo.entities.Media;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.MediaService;
import com.example.demo.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final MediaService mediaService;

    ProductServiceImpl(ProductRepository productRepository, ObjectMapper objectMapper, MediaService mediaService) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
        this.mediaService = mediaService;
    }


    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(String product, MultipartFile[] images) throws JsonProcessingException {
        Product productToSave = this.objectMapper.readValue(product, Product.class);
        List<Media> productImages = mediaService.upload(images);
        productToSave.setProductImages(productImages);
        return productRepository.save(productToSave);
    }


    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setBasePrice(productDetails.getBasePrice());
            existingProduct.setStockQuantity(productDetails.getStockQuantity());
            existingProduct.setCategory(productDetails.getCategory());
            existingProduct.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }



    public byte[] getImageByName(String imageName) throws IOException {
        String uploadDir = "D:/PFA2/Eventify-images"; // your folder path
        Path imagePath = Paths.get(uploadDir, imageName);
        return Files.readAllBytes(imagePath);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String productName) {
        // Fetch the product using the repository
        return productRepository.findByName(productName);
    }


}
