package com.tap.travelfareservice.domain;

import java.util.UUID;

import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TravelFare {
    private UUID id;
    private Double cheapestCost;
    private TravelFareData travelFareData;
    private Driver cheapestDriver;
}
