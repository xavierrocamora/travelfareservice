package com.tap.travelfareservice.TravelFare;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.TravelFare;
import com.tap.travelfareservice.domain.TravelFareData;
import com.tap.travelfareservice.domain.VehicleType;
import com.tap.travelfareservice.repository.TravelFareDataAccessService;
import com.tap.travelfareservice.repository.TravelFareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TravelFareRepositoryTest {

    private TravelFareRepository underTest;

    @BeforeEach
    public void setUp() {
        underTest = new TravelFareDataAccessService("/travelFareDataTest.csv", "/cheapestFaresTest.csv");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/travelFareDataTest.csv", numLinesToSkip = 1)
    public void itShouldReadCSVFile(double distanceTraveled, double traveledUnit, double costPerDistanceTraveled) {
        //given
        TravelFareData travelFareData = new TravelFareData(
                distanceTraveled,
                traveledUnit,
                costPerDistanceTraveled
        );

        //when
        TravelFareData expected = this.underTest.getTravelFareData();
        System.out.println(expected);

        //then
        assertThat(expected).isEqualTo(travelFareData);
    }

    @Test
    public void itShouldSaveATravelFare() {
        // given
        TravelFare travelFare = new TravelFare();
        UUID id = UUID.randomUUID();
        Driver driver = new Driver(
                "John",
                "Hawkings",
                "hawkings@gmail",
                VehicleType.TAXI,
                300.0,
                250.0);
        travelFare.setId(id);
        travelFare.setCheapestCost(300.0);
        travelFare.setTravelFareData(new TravelFareData(250.0, 50.0, 200.0));
        travelFare.setCheapestDriver(driver);

        // when
        underTest.saveCheapestTravelFare(travelFare);

        //then

    }
}
