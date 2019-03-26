package com.petproject.service;

import java.util.List;

import com.petproject.api.ImageSource;
import com.petproject.repository.ImageSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageSourceService {
    private ImageSourceRepository imageSourceRepository;

    @Autowired
    public ImageSourceService(ImageSourceRepository imageSourceRepository) {
        this.imageSourceRepository = imageSourceRepository;
    }

    public List<ImageSource> getImagesBySpot(long spotId){
        return imageSourceRepository.getAllBySpotId(spotId);
    }
}
