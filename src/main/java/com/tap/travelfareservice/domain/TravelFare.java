package com.tap.travelfareservice.domain;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TravelFare {
    private Long id;
    private Double cheapestCost;
    private TravelFareData travelFareData;
    private Driver cheapestDriver;
}
