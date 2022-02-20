package com.tap.travelfareservice.domain;

import java.util.List;

public class FareComparator {

    private List<Driver> drivers;
    private TravelFareData travelFareData;

    public FareComparator(List<Driver> drivers, TravelFareData travelFareData) {
        this.drivers = drivers;
        this.travelFareData = travelFareData;
    }

    public TravelFare calculateCheapestFare(){
        return null;
    };

    public Double calculateFarePerDriver(Driver driver){
        Double distanceTraveledUnits =
                travelFareData.getDistanceTraveled() - driver.getBaseFareDistance() < 0
                ? 0
                : (travelFareData.getDistanceTraveled() - driver.getBaseFareDistance())/ travelFareData.getTraveledUnit();
        return driver.getBaseFarePrice() + (distanceTraveledUnits * travelFareData.getCostPerDistanceTraveled());
    };

}
