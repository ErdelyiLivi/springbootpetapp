package com.petproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.petproject.api.Continent;
import com.petproject.api.Spot;
import com.petproject.constants.Constants;
import com.petproject.dto.SpotDto;
import com.petproject.service.SpotService;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by livi on 16/07/2017.
 */
//@RequestMapping("/spot")
@RestController
public class SpotController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private SpotService spotService;

    @RequestMapping("/restgreeting")
    public List<Spot> greeting(@RequestParam(value = "continent") String continent, @RequestParam(value = "visited") String visited) {
        return spotService.findByContinent(Continent.valueOf(continent.toUpperCase()), "true".equals(visited) ? true : false);
    }

    @RequestMapping(Constants.GET_ALL_SPOTS)
    public List<Spot> getAllSpots(){
        return spotService.getAllSpots();
    }

    @RequestMapping(value= Constants.SAVE_SPOT, method= RequestMethod.POST)
    public void saveSpot(@RequestBody SpotDto spotDto) {
        spotService.saveSpot(spotDto);
    }

    @RequestMapping(value = Constants.GET_SPOT_BY_ID, method = RequestMethod.GET)
    public SpotDto getSpotDtoById(@PathVariable Integer spotId) {
        return spotService.getSpotDtoById(spotId);
    }

    @RequestMapping(value = Constants.UPDATE_SPOT, method = RequestMethod.PUT)
    public ResponseEntity<?> updateSpot(@PathVariable("id") long id, @RequestBody SpotDto spotDto) {
        //logger.info("Updating User with id {}", id);

        Spot spot = spotService.findSpotById(id);

        if (spot == null) {
            //logger.error("Unable to update. User with id {} not found.", id);
            //return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        spot.setName(spotDto.getName());
        spot.setCity(spotDto.getCity());
        spot.setCountry(spotDto.getCountry());
        //spot.setContinent(spotDto.getContinent());


        spotService.updateSpot(spot);
        return new ResponseEntity<>(spot, HttpStatus.OK);
    }
}
