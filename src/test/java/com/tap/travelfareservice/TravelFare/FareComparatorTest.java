package com.tap.travelfareservice.TravelFare;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.FareComparator;
import com.tap.travelfareservice.domain.TravelFareData;
import com.tap.travelfareservice.domain.VehicleType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class FareComparatorTest {

    @Test
    public void itCalculatesBaseFarePriceIfDistanceSmallerThanBaseFareDistance() {
        // given
        TravelFareData travelFareData = new TravelFareData(200, 50, 100);
        Driver driver = new Driver(
                "John",
                "Hawkings",
                "hawkings@gmail",
                VehicleType.CAR,
                300.0,
                250.0);
        List<Driver> list = new ArrayList<>();
        list.add(driver);
        FareComparator fareComparator = new FareComparator(list, travelFareData);

        // then
        assertEquals(driver.getBaseFarePrice(), fareComparator.calculateFarePerDriver(driver));
    }

    @Test
    public void itCalculatesFareIfDistanceBiggerThanBaseFareDistance() {
        // given
        TravelFareData travelFareData = new TravelFareData(200, 50, 100);
        Driver driver = new Driver(
                "John",
                "Hawkings",
                "hawkings@gmail",
                VehicleType.CAR,
                200.0,
                150.0);
        List<Driver> list = new ArrayList<>();
        list.add(driver);
        FareComparator fareComparator = new FareComparator(list, travelFareData);

        // then
        assertEquals(300.0, fareComparator.calculateFarePerDriver(driver));
    }
}
