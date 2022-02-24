package com.tap.travelfareservice.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.TravelFare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import org.mockito.Mockito.*;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.ResultMatcher.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelFareIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void canGetCheapestFare() throws Exception {
        // given

        //when
        ResultActions resultActions = mockMvc
                .perform(get("/api/v1/fares/cheapest").contentType(MediaType.APPLICATION_JSON));

        // then
        MvcResult getCheapestFareResult = resultActions
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = getCheapestFareResult
                .getResponse()
                .getContentAsString();

        TravelFare travelFare = objectMapper.readValue(
                contentAsString,
                new TypeReference<>() {
                }
        );

        assertEquals("culkin@gmail.com", travelFare.getCheapestDriver().getEmail());
        assertEquals(150.0, travelFare.getCheapestCost());
    }
}
