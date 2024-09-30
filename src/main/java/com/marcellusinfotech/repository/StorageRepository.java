package com.marcellusinfotech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcellusinfotech.entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}
