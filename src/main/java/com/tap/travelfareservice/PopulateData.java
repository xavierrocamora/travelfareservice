package com.tap.travelfareservice;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.VehicleType;
import com.tap.travelfareservice.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PopulateData implements CommandLineRunner {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public void run(String... args) throws Exception {
        driverRepository.deleteAll();

        driverRepository.save(new Driver("John", "Sunders", "sunders@gmail.com", VehicleType.TAXI,200.0,150.0));
        driverRepository.save(new Driver("Anna", "Culkin", "culkin@gmail.com", VehicleType.TAXI,150.0,250.0));
        driverRepository.save(new Driver("Anthony", "Smith", "smith@gmail.com", VehicleType.TAXI,300.0,200.0));

        driverRepository.findAll().forEach((driver) -> {
            log.info("{}", driver);
        });
    }
}
