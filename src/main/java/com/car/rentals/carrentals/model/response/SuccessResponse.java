package com.car.rentals.carrentals.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessResponse {

    DELETE("Successfully deleted!");

    private String successResponse;
}
