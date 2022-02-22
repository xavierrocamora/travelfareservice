package com.tap.travelfareservice.repository;

import com.tap.travelfareservice.domain.TravelFareData;
import com.tap.travelfareservice.domain.TravelFare;

public interface TravelFareRepository {

    TravelFareData getTravelFareData();

    boolean saveCheapestTravelFare(TravelFare travelFare);
}
