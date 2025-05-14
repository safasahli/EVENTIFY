package com.example.demo.controllers;

import com.example.demo.entities.ServicesEntity;
import com.example.demo.serviceImpl.EvntifyServicesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/services")
public class EventifyServicesController {
    @Autowired
    private EvntifyServicesService servicesService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<ServicesEntity> getAllServices() {
        return servicesService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesEntity> getServiceById(@PathVariable Long id) {
        return servicesService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServicesEntity createService(@RequestPart("service") String serviceJson ,@RequestPart("images") MultipartFile[] imageFiles) throws JsonProcessingException {
        ServicesEntity services = objectMapper.readValue(serviceJson, ServicesEntity.class);
        return servicesService.createService(services ,imageFiles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicesEntity> updateService(@PathVariable Long id, @RequestBody ServicesEntity service) {
        try {
            return ResponseEntity.ok(servicesService.updateService(id, service));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        servicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}

