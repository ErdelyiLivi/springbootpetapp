package com.petproject.dto;

import com.petproject.api.Continent;
import com.petproject.api.Spot;

public class SpotConverter {
    public static Spot dtoToEntity(SpotDto spotDto) {
        // TODO save visited
        return new Spot(spotDto.getSpotId(), spotDto.getName(), Continent.valueOf(spotDto.getContinent()), spotDto.getCountry(), spotDto.getCity(), true);
    }
    public static SpotDto entityToDto(Spot spot) {
        return new SpotDto(spot.getId(), spot.getName(), spot.getCountry(), spot.getCity(), spot.getContinent().getDisplayName());
    }
}

