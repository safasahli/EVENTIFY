package com.example.demo.serviceImpl;

import com.example.demo.entities.BookableResource;
import com.example.demo.repositories.BookableResourceRepository;
import com.example.demo.services.BookableResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookableResourceServiceImpl implements BookableResourceService {

    private final BookableResourceRepository bookableResourceRepository;

    @Override
    public List<BookableResource> getAllBookableResources() {
        return bookableResourceRepository.findAll();
    }

    @Override
    public Optional<BookableResource> getBookableResourceById(Long id) {
        return bookableResourceRepository.findById(id);
    }

    @Override
    public BookableResource saveBookableResource(BookableResource bookableResource) {
        return bookableResourceRepository.save(bookableResource);
    }

    @Override
    public void deleteBookableResource(Long id) {
        bookableResourceRepository.deleteById(id);
    }
}
