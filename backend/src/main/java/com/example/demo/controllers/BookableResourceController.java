package com.example.demo.controllers;

import com.example.demo.entities.BookableResource;
import com.example.demo.services.BookableResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookable-resources")
@RequiredArgsConstructor
public class BookableResourceController {

    private final BookableResourceService bookableResourceService;

    @GetMapping
    public ResponseEntity<List<BookableResource>> getAllBookableResources() {
        List<BookableResource> resources = bookableResourceService.getAllBookableResources();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookableResource> getBookableResourceById(@PathVariable Long id) {
        return bookableResourceService.getBookableResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookableResource> createOrUpdateBookableResource(@RequestBody BookableResource resource) {
        BookableResource savedResource = bookableResourceService.saveBookableResource(resource);
        return ResponseEntity.ok(savedResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookableResource(@PathVariable Long id) {
        bookableResourceService.deleteBookableResource(id);
        return ResponseEntity.noContent().build();
    }
}
