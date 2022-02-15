package com.tap.travelfareservice.repository;

import com.tap.travelfareservice.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findDriverById(Long id);
}
