package com.tap.travelfareservice.service;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.exception.BadRequestException;
import com.tap.travelfareservice.exception.DriverNotFoundException;
import com.tap.travelfareservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver addDriver(Driver driver) {
        Boolean existsEmail = driverRepository
                .selectExistsEmail(driver.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + driver.getEmail() + " taken"
            );
        }
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return  driverRepository.findAll();
    }

    public Driver getDriverById(Long driverId) {
        return driverRepository.findDriverById(driverId)
                .orElseThrow(() -> new DriverNotFoundException(
                        "Driver with id " + driverId + " does not exist"
                        )
                );
    }

    public Driver updateDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long driverId) {
        if(!driverRepository.existsById(driverId)) {
            throw new DriverNotFoundException(
                    "Driver with id " + driverId + " does not exist"
            );
        }
        driverRepository.deleteById(driverId);
    }
}
