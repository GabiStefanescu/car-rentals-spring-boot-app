package com.car.rentals.carrentals.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;



@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity(name = "vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", nullable = false, length = 7)
    private int serialNumber;

    @Column(nullable = false, length = 20)
    private String model;

    @Column(name = "rented_status", length = 5)
    private boolean isRented;

    @Column(name = "price_per_day", nullable = false, length = 5)
    private double price;

    @Column(name = "rented_date", length = 30)
    private LocalDateTime rentedDate;

    @Column(name = "return_date", length = 30)
    private LocalDateTime returnDate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
