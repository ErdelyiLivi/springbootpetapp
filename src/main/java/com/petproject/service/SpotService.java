package com.petproject.service;

import com.petproject.api.Continent;
import com.petproject.api.Spot;
import com.petproject.dto.SpotDto;
import com.petproject.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ler on 2017-04-02.
 */
@Service
public class SpotService {

    private SpotRepository spotRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public List<Spot> getAllSpots() {
        //TODO mapping for continent here or in angular with translation
        return spotRepository.findAll();
    }

    public void saveSpot(SpotDto spotDto) {
        //TODO make continent nullsafe
        spotRepository.save(new Spot(spotDto.getSpotId(), spotDto.getName(), Continent.valueOf(spotDto.getContinent().toUpperCase()), spotDto.getCountry(), spotDto.getCity(), false));
    }

    public SpotDto getSpotDtoById(long id) {
        if (id == 0) {
            return new SpotDto();
        } else {
            Spot spot = spotRepository.findById(id).orElse(new Spot());
            return new SpotDto(spot.getId(), spot.getName(), spot.getCity(), spot.getCountry(), spot.getContinent().getDisplayName());
        }
    }

    public Spot getSpotById(long id){
        return spotRepository.getOne(id);
    }


}
