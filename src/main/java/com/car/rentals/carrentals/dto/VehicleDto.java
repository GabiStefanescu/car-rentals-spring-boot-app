package com.car.rentals.carrentals.dto;

import com.car.rentals.carrentals.entities.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class VehicleDto {

    private Long id;
    private int serialNumber;
    private String model;
    private boolean isRented;
    private double price;
    private LocalDateTime rentedDate;
    private LocalDateTime returnDate;
    private CustomerEntity customer;
}
