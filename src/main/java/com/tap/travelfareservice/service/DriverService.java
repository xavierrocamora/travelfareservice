package com.tap.travelfareservice.service;

import com.tap.travelfareservice.domain.Driver;
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
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return  driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findDriverById(id);
    }

    public Driver updateDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }


}
