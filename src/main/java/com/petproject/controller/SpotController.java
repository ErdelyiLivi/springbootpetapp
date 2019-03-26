package com.petproject.controller;

import com.petproject.api.ImageSource;
import com.petproject.api.Spot;
import com.petproject.constants.Constants;
import com.petproject.dto.SpotDto;
import com.petproject.service.ImageSourceService;
import com.petproject.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Spot> getAllSpots(){
        return spotService.getAllSpots();
    }

    @PostMapping(value= Constants.SAVE_SPOT)
    public void saveSpot(@RequestBody SpotDto spotDto) {
        spotService.saveSpot(spotDto);
    }

    @GetMapping(value = Constants.GET_SPOT_BY_ID)
    public SpotDto getSpotDtoById(@PathVariable Integer spotId) {
        return spotService.getSpotDtoById(spotId);
    }

    @GetMapping(value = Constants.GET_IMAGES)
    public List<ImageSource> getImagesBySpotId(@PathVariable Long spotId){
        return imageSourceService.getImagesBySpot(spotId);
    }
}
