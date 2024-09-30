package com.marcellusinfotech.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marcellusinfotech.entity.ImageData;
import com.marcellusinfotech.repository.StorageRepository;
import com.marcellusinfotech.utils.ImageUtils;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public ImageData downloadImageById(Long id) throws IOException {
        Optional<ImageData> dbImageDataOptional = repository.findById(id);

        if (dbImageDataOptional.isPresent()) {
            ImageData dbImageData = dbImageDataOptional.get();
            byte[] compressedImageData = dbImageData.getImageData();
            
            if (compressedImageData != null && compressedImageData.length > 0) {
                dbImageData.setImageData(ImageUtils.decompressImage(compressedImageData));  // Decompressing
                return dbImageData;
            } else {
                throw new IOException("Image data is empty or corrupted for ID: " + id);
            }
        } else {
            throw new FileNotFoundException("Image not found for ID: " + id);  // Custom Exception
        }
    }

}
