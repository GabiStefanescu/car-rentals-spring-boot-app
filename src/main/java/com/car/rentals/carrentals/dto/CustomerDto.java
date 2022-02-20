package com.car.rentals.carrentals.dto;

import com.car.rentals.carrentals.entities.VehicleEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String ssn;
    private VehicleEntity vehicle;
}
