package com.tap.travelfareservice.api;

import com.tap.travelfareservice.domain.Driver;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fares")
@AllArgsConstructor
public class TravelFareController {

    @GetMapping(path = "/cheapest")
    public ResponseEntity<?> getCheapestFare() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
