package com.car.rentals.carrentals.service;

import com.car.rentals.carrentals.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    VehicleDto createVehicle(VehicleDto vehicleDto);
    List<VehicleDto> getVehicles();
    List<VehicleDto> getRentedVehicles();
    void deleteVehicle(int serialNumber);
    VehicleDto updateVehicle(VehicleDto vehicleDto, int serialNumber);
}
