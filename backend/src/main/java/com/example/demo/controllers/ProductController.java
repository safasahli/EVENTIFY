package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile) {
        // DEBUG: Print received data
        System.out.println("Received product: " + product);
        System.out.println("File info: " + imageFile.getOriginalFilename() + " (" + imageFile.getSize() + " bytes)");
        try {
            System.out.println(product);
            Product product1 = productService.createProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "Product controller is running";
    }


    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
        try {
            // Fetch the product by its name
            Product product = productService.getProductByName(productName);

            if (product == null) {
                return ResponseEntity.notFound().build(); // If the product doesn't exist
            }

            return ResponseEntity.ok(product); // Return the product details
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Handle error appropriately
        }
    }
    @GetMapping("/products/{productName}/image")
    public ResponseEntity<Map<String, Object>> getProductImage(@PathVariable String productName) {
        Product product = productService.getProductByName(productName);
        if (product != null && product.getImageData() != null) {
            // Prepare response with both image data and image name
            Map<String, Object> response = new HashMap<>();
            response.put("imageData", product.getImageData());
            response.put("imageName", product.getImageName()); // Assuming the image name is stored in the product entity

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // Use application/json for returning both image data and image name
                    .body(response);
        } else {
            return ResponseEntity.notFound().build(); // If no product or image found
        }
    }


}







