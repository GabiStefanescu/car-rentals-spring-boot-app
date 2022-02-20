package com.car.rentals.carrentals.service.impl;

import com.car.rentals.carrentals.dto.CustomerDto;
import com.car.rentals.carrentals.dto.VehicleDto;
import com.car.rentals.carrentals.entities.CustomerEntity;
import com.car.rentals.carrentals.entities.VehicleEntity;
import com.car.rentals.carrentals.exceptions.CustomerServiceException;
import com.car.rentals.carrentals.exceptions.VehicleServiceException;
import com.car.rentals.carrentals.model.response.ErrorMessages;
import com.car.rentals.carrentals.repositories.CustomerRepository;
import com.car.rentals.carrentals.repositories.VehicleRepository;
import com.car.rentals.carrentals.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;


    @Override
    public CustomerDto rentVehicle(CustomerDto customerDto, int serialNumber) {

        if (vehicleRepository.findBySerialNumber(serialNumber) == null) {
            throw new VehicleServiceException(ErrorMessages.DUPLICATE_VEHICLE.getErrorMessage());
        }

        if (customerRepository.findBySsn(customerDto.getSsn()) != null) {
            throw new CustomerServiceException(ErrorMessages.INVALID_CUSTOMER.getErrorMessage());
        }

        if (vehicleRepository.findBySerialNumber(serialNumber).isRented() == true) {
            throw new VehicleServiceException(ErrorMessages.VEHICLE_RENTED.getErrorMessage());
        }

        CustomerEntity customerEntity = new ModelMapper().map(customerDto, CustomerEntity.class);
        VehicleEntity rentedVehicle = vehicleRepository.findBySerialNumber(serialNumber);
        rentedVehicle.setRented(true);
        rentedVehicle.setRentedDate(LocalDateTime.now());

        rentedVehicle.setCustomer(customerEntity);

        customerEntity.setVehicle(rentedVehicle);

        customerRepository.save(customerEntity);
        return new ModelMapper().map(customerEntity, CustomerDto.class);
    }

    @Override
    public void returnVehicle(String ssn) {
        CustomerEntity customerEntity = customerRepository.findBySsn(ssn);
        if (customerEntity == null) {
            throw new CustomerServiceException(ErrorMessages.INVALID_FOR_RETURN.getErrorMessage());
        }

        if (customerEntity.getVehicle() == null) {
            throw new VehicleServiceException(ErrorMessages.NO_VEHICLE_RENTED.getErrorMessage());
        }

        VehicleEntity vehicleEntity = vehicleRepository.findBySerialNumber(customerEntity.getVehicle().getSerialNumber());
        vehicleEntity.setReturnDate(LocalDateTime.now());
        vehicleEntity.setRented(false);
        vehicleEntity.setCustomer(null);
        vehicleRepository.save(vehicleEntity);
    }

    @Override
    public List<VehicleDto> getAvailableVehicles() {
        List<VehicleEntity> vehicles = vehicleRepository.findAll();

        return vehicles.stream()
                .map(vehicleEntity -> new ModelMapper().map(vehicleEntity, VehicleDto.class))
                .filter(vehicleDto -> vehicleDto.isRented() == false)
                .collect(Collectors.toList());
    }
}
