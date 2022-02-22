package com.tap.travelfareservice.service;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.FareComparator;
import com.tap.travelfareservice.domain.TravelFare;
import com.tap.travelfareservice.domain.TravelFareData;
import com.tap.travelfareservice.exception.ServerInternalException;
import com.tap.travelfareservice.repository.DriverRepository;
import com.tap.travelfareservice.repository.TravelFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TravelFareService {
    private final TravelFareRepository travelFareRepository;
    private final DriverRepository driverRepository;
    private final FareComparator fareComparator;

    @Autowired
    public TravelFareService(TravelFareRepository travelFareRepository,
                             DriverRepository driverRepository,
                             FareComparator fareComparator) {
        this.travelFareRepository = travelFareRepository;
        this.driverRepository = driverRepository;
        this.fareComparator = fareComparator;
    }

    public TravelFare getCheapestFare() {
        // Get data
        TravelFareData travelFareData = travelFareRepository.getTravelFareData();
        if (travelFareData == null) {
            throw new ServerInternalException("Could not get travel fare data.");
        }
        List<Driver> drivers = driverRepository.findAll();

        // Calculate cheapest fare
        fareComparator.setDrivers(drivers);
        fareComparator.setTravelFareData(travelFareData);
        TravelFare travelFare = fareComparator.calculateCheapestFare();

        // Save data
        if(!travelFareRepository.saveCheapestTravelFare(travelFare)) {
            throw  new ServerInternalException("Could not save cheapest travel fare.");
        }

        return travelFare;
    }
}
