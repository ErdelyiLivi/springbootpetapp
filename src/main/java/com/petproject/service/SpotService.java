package com.petproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petproject.api.Continent;
import com.petproject.api.Spot;
import com.petproject.dto.SpotDto;
import com.petproject.repository.SpotRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by ler on 2017-04-02.
 */
@Service
public class SpotService {

    @Autowired
    private SpotRepository spotRepository;

    @PersistenceContext
    private EntityManager em;


    //@Transactional(readOnly=true)
    public List<Spot> getAllSpots() {
        return spotRepository.findAll();
    }

    public List<Spot> findByContinent(Continent continent, boolean visited) {

        TypedQuery query = em.createQuery("select a from Spot a where a.continent = :continent and visited = :visited", Spot.class);
        query.setParameter("continent", continent);
        query.setParameter("visited", visited);

        return query.getResultList();
    }

    public List<String> getCountries(List<Spot> spotList){
        List<String> countries = new ArrayList<>();
        for(Spot spot : spotList){
            if(!countries.contains(spot.getCountry())){
                countries.add(spot.getCountry());
            }
        }
        return countries;
    }

    public List<Spot> findByCountry(String country, boolean visited){
        TypedQuery query = em.createQuery("select a from Spot a where a.country = :country and visited = :visited", Spot.class);
        query.setParameter("country", country);
        query.setParameter("visited", visited);
        return query.getResultList();
    }

    @Transactional
    public void saveEntity (Spot spot) {
        em.persist(spot);
    }

    public void saveSpot(SpotDto spotDto){
        //TODO make continent nullsafe
        if(spotDto.getSpotId() != null){
            spotRepository.save(new Spot(spotDto.getSpotId(), spotDto.getName(), Continent.valueOf(spotDto.getContinent().toUpperCase()), spotDto.getCity(), spotDto.getCountry(), false));
        } else {
            spotRepository.save(new Spot(spotDto.getName(), Continent.valueOf(spotDto.getContinent().toUpperCase()), spotDto.getCountry(), spotDto.getCity()));
        }
    }

    public void saveSpot(Spot spot) {
        spotRepository.save(spot);
    }

    public void updateSpot(Spot spot){
        saveSpot(spot);
    }

    public Spot findSpotById(long id){
        return spotRepository.findById(id).orElse(new Spot());
    }

    public SpotDto getSpotDtoById(long id){
        Spot spot = spotRepository.findById(id).orElse(new Spot());
        return new SpotDto(spot.getId(), spot.getName(), spot.getCity(), spot.getCountry(), spot.getContinent().getDisplayName());
        //return new SpotDto(spot.getName(), spot.getCity(), spot.getCountry(), spot.getContinent().getDisplayName());
    }



}
