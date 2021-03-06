package com.petproject.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.petproject.api.ImageSource;
import com.petproject.api.Spot;
import com.petproject.repository.ImageSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageSourceService {

    @Value("${pictures.path}")
    private String picturesDirectory;
    private ImageSourceRepository imageSourceRepository;
    private ResourceLoader resourceLoader;
    private SpotService spotService;

    @Autowired
    public ImageSourceService(ImageSourceRepository imageSourceRepository, ResourceLoader resourceLoader,
                              SpotService spotService) {
        this.imageSourceRepository = imageSourceRepository;
        this.resourceLoader = resourceLoader;
        this.spotService = spotService;
    }

    public List<ImageSource> getImagesBySpot(long spotId){
        return imageSourceRepository.getAllBySpotId(spotId);
    }

    public void storeFile(MultipartFile file) throws IOException {
        Path filePath = Paths.get(picturesDirectory + file.getOriginalFilename());

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

    }

    public Resource loadAsResource(String filename){
        return resourceLoader.getResource("file:"+ picturesDirectory + filename);
    }

    public void saveFileData(String name, String url, Long spotId) {
        //TODO find less db call solution
        Spot spotDB = spotService.getSpotById(spotId);
        imageSourceRepository.save(new ImageSource(name, url, spotDB));
    }
}
