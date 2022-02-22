package com.tap.travelfareservice.domain;

import java.util.*;
import java.util.stream.Collectors;

public class FareComparator {

    private final List<Driver> drivers;
    private final TravelFareData travelFareData;

    public FareComparator(List<Driver> drivers, TravelFareData travelFareData) {
        this.drivers = drivers;
        this.travelFareData = travelFareData;
    }

    public TravelFare calculateCheapestFare(){
        // Create map  key = driver , value = calculated fare for that driver
        Map<Driver, Double> faresPerDriverMap = drivers.stream()
                .collect(
                Collectors.toMap(d -> d, d -> calculateFarePerDriver(d)));

        // Get the minimum fare cost and the associated driver
        Map.Entry<Driver, Double> min = Collections.min(faresPerDriverMap.entrySet(),
                Map.Entry.comparingByValue());

        System.out.println("Min :" + min.getValue());
        System.out.println("Driver :" + min.getKey());

        return mapTravelFare(min.getKey(), min.getValue());
    }

    public Double calculateFarePerDriver(Driver driver){
        Double distanceTraveledUnits =
                travelFareData.getDistanceTraveled() - driver.getBaseFareDistance() < 0
                ? 0
                : (travelFareData.getDistanceTraveled() - driver.getBaseFareDistance())/ travelFareData.getTraveledUnit();

        return driver.getBaseFarePrice() + (distanceTraveledUnits * travelFareData.getCostPerDistanceTraveled());
    }

    private TravelFare mapTravelFare(Driver driver, Double fareCost) {
        TravelFare travelFare = new TravelFare();
        UUID id = UUID.randomUUID();
        travelFare.setId(id);
        travelFare.setCheapestCost(fareCost);
        travelFare.setTravelFareData(travelFareData);
        travelFare.setCheapestDriver(driver);

        return travelFare;
    }
}
