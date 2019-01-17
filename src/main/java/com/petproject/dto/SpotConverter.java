package com.petproject.dto;

import com.petproject.api.Spot;

//TODO utilise this
public class SpotConverter {
    public static Spot dtoToEntity(SpotDto spotDto) {
        Spot spot = new Spot();
        //spot.setSkills(spotDto.getSkillDtos().stream().map(SkillConverter::dtoToEntity).collect(Collectors.toList()));
        return spot;
    }
    public static SpotDto entityToDto(Spot spot) {
        SpotDto userDto = new SpotDto();
        //userDto.setSkillDtos(spot.getSkills().stream().map(SkillConverter::entityToDto).collect(Collectors.toList()));
        return userDto;
    }
}

