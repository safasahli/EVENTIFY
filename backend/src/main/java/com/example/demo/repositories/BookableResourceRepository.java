package com.example.demo.repositories;

import com.example.demo.entities.BookableResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookableResourceRepository extends JpaRepository<BookableResource, Long> {
}
