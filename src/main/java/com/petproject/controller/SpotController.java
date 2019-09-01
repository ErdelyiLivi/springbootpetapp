package com.petproject.controller;

import java.io.IOException;
import java.util.List;

import com.petproject.api.ImageSource;
import com.petproject.api.Spot;
import com.petproject.constants.Constants;
import com.petproject.dto.SpotDto;
import com.petproject.service.ImageSourceService;
import com.petproject.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by livi on 16/07/2017.
 */

@RestController
public class SpotController {

    @Autowired
    private SpotService spotService;

    @Autowired
    private ImageSourceService imageSourceService;

    @RequestMapping(Constants.GET_ALL_SPOTS)
    public List<Spot> getAllSpots() {
        return spotService.getAllSpots();
    }

    @PostMapping(value = Constants.SAVE_SPOT)
    public void saveSpot(@RequestBody SpotDto spotDto) {
        spotService.saveSpot(spotDto);
    }

    @GetMapping(value = Constants.GET_SPOT_BY_ID)
    public SpotDto getSpotDtoById(@PathVariable Integer spotId) {
        return spotService.getSpotDtoById(spotId);
    }

    @GetMapping(value = Constants.GET_IMAGES)
    public List<ImageSource> getImagesBySpotId(@PathVariable Long spotId) {
        return imageSourceService.getImagesBySpot(spotId);
    }

    @PostMapping(value = "/api/files/{spotId}")
    @ResponseStatus(HttpStatus.OK)
    public void handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Long spotId) throws IOException {
        imageSourceService.storeFile(file);
        String url = "http://localhost:8080/files/" + file.getOriginalFilename();
        imageSourceService.saveFileData(file.getOriginalFilename(), url, spotId);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource resource = imageSourceService.loadAsResource(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
