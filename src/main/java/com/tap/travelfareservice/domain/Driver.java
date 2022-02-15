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
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String vehicleType;
    private Double baseFarePrice;
    private Double baseFareDistance;
}
