package com.car.rentals.carrentals.service;

import com.car.rentals.carrentals.dto.CustomerDto;
import com.car.rentals.carrentals.dto.VehicleDto;

import java.util.List;

public interface CustomerService {
    CustomerDto rentVehicle(CustomerDto customerDto, int serialNumber);
    void returnVehicle(String ssn);
    List<VehicleDto> getAvailableVehicles();
}
