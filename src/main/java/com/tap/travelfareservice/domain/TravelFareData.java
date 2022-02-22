package com.tap.travelfareservice.domain;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TravelFareData {
    private double distanceTraveled;
    private double traveledUnit;
    private double costPerDistanceTraveled;
}
