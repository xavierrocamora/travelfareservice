package com.tap.travelfareservice.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.javafaker.Faker;
import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.VehicleType;
import com.tap.travelfareservice.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class DriverIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private DriverRepository driverRepository;

    // Faker allows to create fake data we will feed the handlers with
    private final Faker faker = new Faker();

    @Test
    void canAddNewDriver() throws Exception {
        // given
        String name = String.format("%s", faker.name().firstName());
        String surname = String.format("%s", faker.name().lastName());
        String email = String.format("%s@gmail.com",
                StringUtils.trimAllWhitespace(surname.trim().toLowerCase()));
        Double baseFarePrice = faker.number()
                .randomDouble(2, 1, 300);
        Double baseFareDistance = faker.number()
                .randomDouble(2, 1, 500);

        Driver driver = new Driver(
                name,
                surname,
                email,
                VehicleType.CAR,
                baseFarePrice,
                baseFareDistance
        );

        // when
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(driver))
                );
        // then
        resultActions.andExpect(status().isOk());
        List<Driver> drivers = driverRepository.findAll();
        assertThat(drivers)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(driver);

    }

    @Test
    void canDeleteDriver () throws Exception {
        // given
        String name = String.format("%s", faker.name().firstName());
        String surname = String.format("%s", faker.name().lastName());
        String email = String.format("%s@gmail.com",
                StringUtils.trimAllWhitespace(surname.trim().toLowerCase()));
        Double baseFarePrice = faker.number()
                .randomDouble(2, 1, 300);
        Double baseFareDistance = faker.number()
                .randomDouble(2, 1, 500);

        Driver driver = new Driver(
                name,
                surname,
                email,
                VehicleType.CAR,
                baseFarePrice,
                baseFareDistance
        );

        // add a new driver
        mockMvc
                .perform(post("/api/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(driver))
                ).andExpect(status().isOk());

        // recover the inserted driver's id
        MvcResult getDriversResult = mockMvc.perform(get("/api/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = getDriversResult
                .getResponse()
                .getContentAsString();

        List<Driver> drivers = objectMapper.readValue(
                contentAsString,
                new TypeReference<>() {
                }
        );

        long id = drivers.stream()
                .filter(d -> d.getEmail().equals(driver.getEmail()))
                .map(Driver::getId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Driver with email: " + email + " not found"));

        // when
        ResultActions resultActions = mockMvc
                .perform(delete("/api/v1/drivers/" + id));

        // then
        resultActions.andExpect(status().isOk());
        boolean exists = driverRepository.existsById(id);
        assertThat(exists).isFalse();
    }
}
