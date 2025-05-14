package com.example.demo.serviceImpl;

import com.example.demo.entities.Media;
import com.example.demo.entities.Product;
import com.example.demo.entities.ServicesEntity;
import com.example.demo.repositories.MediaRepository;
import com.example.demo.repositories.ServicesRepository;
import com.example.demo.services.MediaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EvntifyServicesService {

    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private MediaService mediaService;

    public List<ServicesEntity> getAllServices() {
        return servicesRepository.findAll();
    }

    public Optional<ServicesEntity> getServiceById(Long id )  {

        return servicesRepository.findById(id);
    }

    public ServicesEntity createService(ServicesEntity service , MultipartFile[] images) throws JsonProcessingException  {

        List<Media> categoryImages = mediaService.upload(images);
        service.setCategoryImages(categoryImages);
        return servicesRepository.save(service);
    }

    public ServicesEntity updateService(Long id, ServicesEntity updatedService) {
        return servicesRepository.findById(id).map(service -> {
            service.setName(updatedService.getName());
            service.setDescription(updatedService.getDescription());
            service.setCategory(updatedService.getCategory());
            return servicesRepository.save(service);
        }).orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
    }

    public void deleteService(Long id) {
        servicesRepository.deleteById(id);
    }
}
