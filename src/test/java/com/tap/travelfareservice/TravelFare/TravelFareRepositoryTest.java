package com.tap.travelfareservice.TravelFare;

import com.tap.travelfareservice.domain.*;
import com.tap.travelfareservice.repository.TravelFareDataAccessService;
import com.tap.travelfareservice.repository.TravelFareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TravelFareRepositoryTest {

    private TravelFareRepository underTest;

    @BeforeEach
    public void setUp() {
        underTest = new TravelFareDataAccessService("/travelFareDataTest.csv", "cheapestFaresTest.csv");
        new File("cheapestFaresTest.csv").delete();
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
    public void itShouldSaveATravelFare() throws IOException {
        // given
        TravelFare travelFare = new TravelFare();
        UUID id = UUID.randomUUID();
        Driver driver = new Driver(
                "John",
                "Hawkings",
                "hawkings@gmail.com",
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
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("cheapestFaresTest.csv"))) {
            String line;
            while((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist.");
            ex.printStackTrace();
        }

        String header = "id,cheapestCost,fareDistance,fareUnit,fareCostUnit,driverId,driverName,driverSurname," +
                "driverEmail,driverVehicleType,driverBaseFareCost,driverBaseFareDistance";
        String fare = id.toString() + ",300.0,250.0,50.0,200.0,null,John,Hawkings,hawkings@gmail.com,TAXI,300.0,250.0";
        assertEquals(2, lines.size());
        assertEquals(header, lines.get(0));
        assertEquals(fare, lines.get(1));
    }
}
