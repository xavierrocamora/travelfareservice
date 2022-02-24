package com.tap.travelfareservice.domain;

import lombok.*;
import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size(min=3,message="Name should have more than 3 characters.")
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Size(min=3,message="Surname should have more than 3 characters.")
    @Column(nullable = false)
    private String surname;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
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
