package com.petproject.dto;

public class SpotDto {
    private Long spotId;
    private String spotName;
    private String country;
    private String city;
    private String continent;

    public SpotDto() {
    }

    public SpotDto(Long spotId, String spotName, String country, String city, String continent) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.country = country;
        this.city = city;
        this.continent = continent;
    }

    public Long getSpotId() {
        return spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getContinent() {
        return continent;
    }
}
