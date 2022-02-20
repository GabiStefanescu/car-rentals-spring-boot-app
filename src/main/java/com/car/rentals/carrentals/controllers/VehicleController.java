package com.car.rentals.carrentals.controllers;

import com.car.rentals.carrentals.dto.CustomerDto;
import com.car.rentals.carrentals.dto.VehicleDto;
import com.car.rentals.carrentals.model.response.AvailableVehiclesResponse;
import com.car.rentals.carrentals.model.response.SuccessResponse;
import com.car.rentals.carrentals.service.CustomerService;
import com.car.rentals.carrentals.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;
    private final CustomerService customerService;

    @PostMapping
    public VehicleDto createVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleService.createVehicle(vehicleDto);
    }

    @GetMapping
    public List<VehicleDto> getVehicles() {
        return vehicleService.getVehicles();
    }

    @PostMapping("/{serialNumber}")
    public CustomerDto rentVehicle(@RequestBody CustomerDto customerDto, @PathVariable int serialNumber) {
        return customerService.rentVehicle(customerDto, serialNumber);
    }

    @GetMapping("/rented")
    public List<VehicleDto> getRentedVehicles() {
        return vehicleService.getRentedVehicles();
    }

    @PutMapping("/return/{ssn}")
    public void returnVehicle(@PathVariable String ssn) {
        customerService.returnVehicle(ssn);
    }

    @GetMapping("/available")
    public List<AvailableVehiclesResponse> getAvailableVehicles() {
        return customerService.getAvailableVehicles().stream()
                .map(vehicleDto -> new ModelMapper().map(vehicleDto, AvailableVehiclesResponse.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{serialNumber}")
    public String deleteVehicle(@PathVariable int serialNumber) {
        vehicleService.deleteVehicle(serialNumber);
        return SuccessResponse.DELETE.getSuccessResponse();

    }

    @PutMapping("/{serialNumber}")
    public VehicleDto updateVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable int serialNumber) {
        return vehicleService.updateVehicle(vehicleDto, serialNumber);
    }

}
