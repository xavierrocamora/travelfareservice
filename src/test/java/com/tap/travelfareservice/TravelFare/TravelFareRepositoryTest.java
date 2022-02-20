package com.tap.travelfareservice.TravelFare;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.TravelFareData;
import com.tap.travelfareservice.domain.VehicleType;
import com.tap.travelfareservice.repository.DriverRepository;
import com.tap.travelfareservice.repository.TravelFareRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TravelFareRepositoryTest {

    @Autowired
    private TravelFareRepository underTest;

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

        //then
        assertThat(expected).isEqualTo(travelFareData);
    }
}
