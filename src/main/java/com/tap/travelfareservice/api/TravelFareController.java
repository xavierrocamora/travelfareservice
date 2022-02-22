package com.tap.travelfareservice.api;

import com.tap.travelfareservice.domain.TravelFare;
import com.tap.travelfareservice.service.TravelFareService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/fares")
@AllArgsConstructor
public class TravelFareController {
    private final TravelFareService travelFareService;

    @GetMapping(path = "/cheapest")
    public ResponseEntity<?> getCheapestFare() {
        TravelFare travelFare = travelFareService.getCheapestFare();
        return new ResponseEntity<>(travelFare, HttpStatus.OK);
    }
}
