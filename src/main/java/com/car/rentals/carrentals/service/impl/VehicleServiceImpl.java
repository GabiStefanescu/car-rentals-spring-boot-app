package com.car.rentals.carrentals.service.impl;

import com.car.rentals.carrentals.dto.VehicleDto;
import com.car.rentals.carrentals.entities.VehicleEntity;
import com.car.rentals.carrentals.exceptions.VehicleServiceException;
import com.car.rentals.carrentals.model.response.ErrorMessages;
import com.car.rentals.carrentals.repositories.VehicleRepository;
import com.car.rentals.carrentals.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;


    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {

        if (vehicleRepository.findBySerialNumber(vehicleDto.getSerialNumber()) != null) {
            throw new VehicleServiceException(ErrorMessages.DUPLICATE_VEHICLE.getErrorMessage());
        }

        vehicleDto.setRented(false);

        VehicleEntity vehicleEntity = new ModelMapper().map(vehicleDto, VehicleEntity.class);
        VehicleEntity storedVehicle = vehicleRepository.save(vehicleEntity);
        return new ModelMapper().map(storedVehicle, VehicleDto.class);
    }

    @Override
    public List<VehicleDto> getVehicles() {
        List<VehicleEntity> vehicles = vehicleRepository.findAll();

        Type listType = new TypeToken<List<VehicleDto>>() {}.getType();
        List<VehicleDto> returnedValue = new ModelMapper().map(vehicles, listType);

        return returnedValue;
    }

    @Override
    public List<VehicleDto> getRentedVehicles() {
        return getVehicles().stream()
                .filter(vehicleDto -> vehicleDto.isRented())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(int serialNumber) {

        if (vehicleRepository.findBySerialNumber(serialNumber) == null) {
            throw new VehicleServiceException(ErrorMessages.INVALID_VEHICLE.getErrorMessage());
        }

        VehicleEntity vehicleEntity = vehicleRepository.findBySerialNumber(serialNumber);

        if (vehicleEntity.isRented()) {
            throw new VehicleServiceException(ErrorMessages.CANNOT_DELETE_VEHICLE.getErrorMessage());
        }

        vehicleRepository.delete(vehicleEntity);
    }

    @Override
    public VehicleDto updateVehicle(VehicleDto vehicleDto, int serialNumber) {

        if (vehicleRepository.findBySerialNumber(serialNumber) == null) {
            throw new VehicleServiceException(ErrorMessages.INVALID_VEHICLE.getErrorMessage());
        }

        VehicleEntity vehicleEntity = vehicleRepository.findBySerialNumber(serialNumber);

        if (vehicleEntity.isRented()) {
            throw new VehicleServiceException(ErrorMessages.VEHICLE_RENTED.getErrorMessage());
        }

        vehicleEntity.setModel(vehicleDto.getModel());
        vehicleEntity.setPrice(vehicleDto.getPrice());

        VehicleEntity storedVehicle = vehicleRepository.save(vehicleEntity);
        return new ModelMapper().map(storedVehicle, VehicleDto.class);
    }
}
