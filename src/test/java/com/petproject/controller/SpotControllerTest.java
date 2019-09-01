package com.petproject.controller;

import com.petproject.api.Spot;
import com.petproject.constants.Constants;
import com.petproject.dto.SpotDto;
import com.petproject.service.ImageSourceService;
import com.petproject.service.SpotService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpotController.class)
public class SpotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpotService spotService;
    @MockBean
    private ImageSourceService imageSourceService;

    @Test
    public void testGetAllSpots() throws Exception {
        //GIVEN
        List<Spot> mockResultList = new ArrayList<>();
        Spot exampleSpot = new Spot();
        mockResultList.add(exampleSpot);

        //WHEN
        when(spotService.getAllSpots()).thenReturn(new ArrayList<>());

        //THEN
        //TODO separate this call to match when-then
        this.mockMvc.perform(get(Constants.GET_ALL_SPOTS)).andExpect(status().isOk());
    }

    @Test
    public void testSaveSpot() throws Exception {

        //GIVEN
        String exampleJson = "{\"spotId\":1,\"name\":\"newangasdf\",\"country\":\"def\",\"city\":\"abccd\",\"continent\":\"Europe\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(Constants.SAVE_SPOT)
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);
        //WHEN
        this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

        //THEN
        verify(spotService).saveSpot(any(SpotDto.class));
    }

    @Test
    public void testGetSpotDtoById() throws Exception {
        //GIVEN
        SpotDto exampleSpot = new SpotDto(1l, "MyTest", "", "", "");

        //WHEN
        when(spotService.getSpotDtoById(anyLong())).thenReturn(exampleSpot);
        MvcResult result = this.mockMvc.perform(get(Constants.GET_SPOT_BY_ID, 1)).andReturn();

        //THEN
        assertThat(result.getResponse().getContentAsString()).contains("MyTest");
    }
}
