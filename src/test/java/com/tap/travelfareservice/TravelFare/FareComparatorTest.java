package com.tap.travelfareservice.TravelFare;

import com.tap.travelfareservice.domain.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class FareComparatorTest {

    private static  final TravelFareData travelFareData = new TravelFareData(200, 50, 100);

    private static  final Driver driver1 = new Driver(
            "John",
            "Hawkings",
            "hawkings@gmail",
            VehicleType.TAXI,
            300.0,
            250.0);

    private static final Driver driver2 = new Driver(
            "Bob",
            "Hawkings",
            "bhawkings@gmail",
            VehicleType.TAXI,
            200.0,
            150.0);

    private static final Driver driver3 = new Driver(
            "Mary",
            "Sunders",
            "sunders@gmail",
            VehicleType.OTHER,
            200.0,
            250.0);

    @Test
    public void itCalculatesBaseFarePriceIfDistanceSmallerThanBaseFareDistance() {
        // given
        List<Driver> list = new ArrayList<>();
        list.add(driver1);
        FareComparator fareComparator = new FareComparator(list, travelFareData);

        // then
        assertEquals(driver1.getBaseFarePrice(), fareComparator.calculateFarePerDriver(driver1));
    }

    @Test
    public void itCalculatesFareIfDistanceBiggerThanBaseFareDistance() {
        // given
        List<Driver> list = new ArrayList<>();
        list.add(driver2);
        FareComparator fareComparator = new FareComparator(list, travelFareData);

        // then
        assertEquals(300.0, fareComparator.calculateFarePerDriver(driver2));
    }

    @Test
    public void itCalculatesCheapestFare() {
        // given
        List<Driver> list = new ArrayList<>();
        list.add(driver1);
        list.add(driver2);
        list.add(driver3);
        FareComparator fareComparator = new FareComparator(list, travelFareData);

        // then
        TravelFare calculated = fareComparator.calculateCheapestFare();
        TravelFare expected = new TravelFare(calculated.getId(), 200.0,travelFareData, driver3);

        assertEquals(expected, calculated);
    }
}
