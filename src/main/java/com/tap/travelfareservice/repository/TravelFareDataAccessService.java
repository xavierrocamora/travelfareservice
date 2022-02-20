package com.tap.travelfareservice.repository;

import com.tap.travelfareservice.domain.TravelFare;
import com.tap.travelfareservice.domain.TravelFareData;
import org.springframework.stereotype.Repository;

@Repository("IO-CSV")
public class TravelFareDataAccessService implements TravelFareRepository{
    @Override
    public TravelFareData getTravelFareData() {
        return null;
    }

    @Override
    public boolean saveCheapestTravelFare(TravelFare travelFare) {
        return false;
    }
}
