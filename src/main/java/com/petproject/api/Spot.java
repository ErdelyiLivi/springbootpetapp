package com.petproject.api;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ler on 2017-03-19.
 */


@Entity
public class Spot implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTINENT")
    @Enumerated(EnumType.STRING)
    private Continent continent;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "VISITED")
    private boolean visited;

    public Spot() {
    }
    //for testing
    public Spot(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Spot(String name, Continent continent, String country, String city) {
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.city = city;
    }

    public Spot(Long id, String name, Continent continent, String country, String city, boolean visited) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.visited = visited;
    }

    public String getCountry() {
        return country;
    }

    public Continent getContinent() {
        return continent;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isVisited() {
        return visited;
    }

}
