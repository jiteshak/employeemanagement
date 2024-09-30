package com.marcellusinfotech.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marcellusinfotech.entity.ImageData;
import com.marcellusinfotech.service.StorageService;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class StorageController {

	@Autowired
	private StorageService service;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
		try {
			String uploadImage = service.uploadImage(file);
			return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error uploading image: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> downloadImageById(@PathVariable Long id) {
	    try {
	        ImageData dbImageData = service.downloadImageById(id);  // Adjusted service to return ImageData
	        byte[] imageData = dbImageData.getImageData();  // Already decompressed by the service
	        return ResponseEntity.status(HttpStatus.OK)
	                .contentType(MediaType.valueOf(dbImageData.getType()))  // Dynamic content type
	                .body(imageData);
	    } catch (FileNotFoundException e) {  // Custom Exception for missing image
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Image not found with ID: " + id);
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error retrieving image with ID: " + id);
	    }
	}
	

}
