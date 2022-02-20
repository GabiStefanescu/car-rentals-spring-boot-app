package com.car.rentals.carrentals.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableVehiclesResponse {
    String serialNumber;
    String model;
    String price;
}
