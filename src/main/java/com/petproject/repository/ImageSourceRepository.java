package com.petproject.repository;

import java.util.List;

import com.petproject.api.ImageSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageSourceRepository extends JpaRepository<ImageSource, Long> {
    public List<ImageSource> getAllBySpotId(long spotId);
}
