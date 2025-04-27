package com.example.demo.serviceImpl;


import com.example.demo.entities.Media;
import com.example.demo.exceptions.FileLoadException;
import com.example.demo.exceptions.FileUploadException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.MediaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
public class MediaServiceImpl implements com.example.demo.services.MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    private final Path root = Paths.get("uploads");
    private final MediaRepository mediaRepository;
    private static final String POINT= ".";
    private static final String PATH_DELIMITER = "/";

    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void createDirectory(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Media upload(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            if(Objects.isNull(originalFilename)){
                throw new FileUploadException("FILE_NAME_CANNOT_BE_EMPTY_OR_NULL");
            }
            String fileExtension = Objects.requireNonNull(file.getOriginalFilename()).substring(originalFilename.lastIndexOf(POINT));
            String folderName = UUID.randomUUID().toString();
            Path path = Path.of(root + PATH_DELIMITER + folderName);
            this.createDirectory(path);
            Media media = new Media();
            media.setName(file.getOriginalFilename());
            media.setExtension(fileExtension);
            media.setSize(file.getSize());
            media.setType(file.getContentType());
            media.setUrl("/uploads/" + folderName + PATH_DELIMITER + file.getOriginalFilename());
            media.setFolderName(folderName);
            Files.copy(file.getInputStream(), path.resolve(media.getName()));
            return mediaRepository.save(media);
        } catch (Exception e) {
            throw new FileUploadException("FILE_UPLOAD_FAILED");
        }
    }

    @Override
    public List<Media> upload(MultipartFile[] files) {
        List<Media> medias = new ArrayList<>();
        for (MultipartFile file : files) {
            Media media = upload(file);
            medias.add(media);
        }
        return medias;
    }

    @Override
    public Resource loadById(Long id) {
        try {
            Media media = mediaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("MEDIA_NOT_FOUND"));
            Path file = root.resolve(media.getUrl().replaceFirst("/uploads/", ""));
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileUploadException("FILE_NOT_READABLE");
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new FileLoadException("FILE_LOAD_FAILED");
        }
    }

}
