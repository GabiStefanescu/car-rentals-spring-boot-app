package com.car.rentals.carrentals.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "ssn", nullable = false, length = 13)
    private String ssn;

    @JsonIgnore
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private VehicleEntity vehicle;
}
