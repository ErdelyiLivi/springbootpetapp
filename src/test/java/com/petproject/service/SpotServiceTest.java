package com.petproject.service;

import com.petproject.api.Continent;
import com.petproject.api.Spot;
import com.petproject.dto.SpotDto;
import com.petproject.repository.SpotRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//TODO imporve test coverage and test quality
public class SpotServiceTest {

    @Mock
    private SpotRepository spotRepository;

    private SpotService spotService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        spotService = new SpotService(spotRepository);
    }

    @Test
    public void getAllSpots() {
        //GIVEN
        //WHEN
        when(spotRepository.findAll()).thenReturn(new ArrayList<>());
        spotService.getAllSpots();
        //THEN
        verify(spotRepository).findAll();
    }

    @Test
    public void saveSpot() {
        //GIVEN
        SpotDto spotDto = new SpotDto(1l, "TestSpot", "", "", "EUROPE");
        //WHEN
        spotService.saveSpot(spotDto);
        //THEN
        verify(spotRepository).save(any());
    }

    @Test
    public void getSpotDtoByIdZero() {
        //GIVEN
        //WHEN
        SpotDto result = spotService.getSpotDtoById(0);
        //THEN
        assertThat(result.getName()).isNullOrEmpty();
        assertThat(result.getCity()).isNullOrEmpty();
        assertThat(result.getContinent()).isNullOrEmpty();
        assertThat(result.getCountry()).isNullOrEmpty();
        assertThat(result.getSpotId()).isNull();

    }

    @Test
    public void getSpotDtoByIdNotZero() {
        //GIVEN
        //WHEN
        Spot exampleSpot = new Spot(1l, "ExampleSpot", Continent.EUROPE, "", "", false);
        when(spotRepository.findById(1l)).thenReturn(Optional.of(exampleSpot));
        SpotDto result = spotService.getSpotDtoById(1l);
        //THEN
        assertThat(result).isNotNull();
        assertThat(result.getSpotId()).isNotNull();
        assertThat(result.getName()).isEqualTo(exampleSpot.getName());
    }
}
