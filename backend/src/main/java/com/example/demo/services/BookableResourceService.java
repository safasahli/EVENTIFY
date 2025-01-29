package com.example.demo.services;

import com.example.demo.entities.BookableResource;

import java.util.List;
import java.util.Optional;

public interface BookableResourceService {
    List<BookableResource> getAllBookableResources();
    Optional<BookableResource> getBookableResourceById(Long id);
    BookableResource saveBookableResource(BookableResource bookableResource);
    void deleteBookableResource(Long id);
}
