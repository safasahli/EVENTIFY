package com.example.demo.services;

import com.example.demo.entities.Media;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface MediaService {
    void init();
    void createDirectory(Path path);
    Media upload(MultipartFile file);
    List<Media> upload(MultipartFile[] files);
    Resource loadById(Long id);
}
