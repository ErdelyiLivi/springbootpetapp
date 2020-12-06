package com.petproject.dto;

public class SpotDto {
    private Long spotId;
    private String name;
    private String country;
    private String city;
    private String continent;
    private String notes;

    public SpotDto() {
    }

    public SpotDto(Long spotId, String name, String country, String city, String continent, String notes) {
        this.spotId = spotId;
        this.name = name;
        this.country = country;
        this.city = city;
        this.continent = continent;
        this.notes = notes;
    }

    public Long getSpotId() {
        return spotId;
    }

    public String getName() {
        return name;
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

    public String getNotes() {
        return notes;
    }

}
