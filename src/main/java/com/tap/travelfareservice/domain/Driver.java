package com.tap.travelfareservice.domain;

import lombok.*;
import javax.persistence.*;
import javax.persistence.GenerationType;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "driver_sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;
    @Column(nullable = false)
    private Double baseFarePrice;
    @Column(nullable = false)
    private Double baseFareDistance;

    public Driver(String name, String surname, String email,
                  VehicleType vehicleType, double baseFarePrice, double baseFareDistance) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.vehicleType = vehicleType;
        this.baseFarePrice = baseFarePrice;
        this.baseFareDistance = baseFareDistance;
    }
}
