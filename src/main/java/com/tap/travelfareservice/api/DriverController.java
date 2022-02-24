package com.tap.travelfareservice.api;

import com.tap.travelfareservice.domain.Driver;
import lombok.AllArgsConstructor;
import com.tap.travelfareservice.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/drivers")
@AllArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable("id") Long id) {
        Driver driver = driverService.getDriverById(id);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Driver> addDriver(@Valid @NonNull @RequestBody Driver driver) {
        Driver newDriver = driverService.addDriver(driver);
        return new ResponseEntity<>(newDriver, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable("id") Long id, @Valid @NonNull @RequestBody Driver driverToUpdate) {
        Driver updateDriver = driverService.updateDriver(id, driverToUpdate);
        return new ResponseEntity<>(updateDriver, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable("id") Long id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
