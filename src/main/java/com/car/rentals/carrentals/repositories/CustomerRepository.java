package com.car.rentals.carrentals.repositories;

import com.car.rentals.carrentals.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findBySsn(String ssn);

}
