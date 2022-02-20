package com.car.rentals.carrentals.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    INVALID_FOR_RETURN("Customer does not appear to be valid for vehicle return!"),
    NO_VEHICLE_RENTED("You didn't rented any vehicle!"),
    VEHICLE_RENTED("Vehicle is rented. Choose another one."),
    INVALID_CUSTOMER("Invalid customer, already in our database."),
    INVALID_VEHICLE("Vehicle not found!"),
    DUPLICATE_VEHICLE("This vehicle record exists."),
    CANNOT_DELETE_VEHICLE("Can't delete a vehicle that is rented.");


    private String errorMessage;



}
