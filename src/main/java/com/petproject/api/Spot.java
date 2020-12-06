package com.petproject.api;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by ler on 2017-03-19.
 */


@Entity
@Table(name = "SPOT")
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

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "VISITED")
    private boolean visited;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL)
    private Set<ImageSource> imageSources;

    public Spot() {
    }
    //for testing
    public Spot(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Spot(Long id, String name, Continent continent, String country, String city, String notes, boolean visited) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.notes = notes;
        this.visited = visited;
    }

    // TODO get rid of unused methods
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
