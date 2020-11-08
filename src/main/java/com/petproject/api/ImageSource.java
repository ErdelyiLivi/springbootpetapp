package com.petproject.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGESOURCE")
public class ImageSource {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;


    public ImageSource() {
    }

    public ImageSource(String name, String url, Spot spot) {
        this.name = name;
        this.url = url;
        this.spot = spot;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Spot getSpot() {
        return spot;
    }
}
