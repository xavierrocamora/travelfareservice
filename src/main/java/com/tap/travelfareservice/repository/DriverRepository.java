package com.tap.travelfareservice.repository;

import com.tap.travelfareservice.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findDriverById(Long id);

    @Query("" +
            "SELECT CASE WHEN COUNT(d) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Driver d " +
            "WHERE d.email = ?1"
    )
    Boolean selectExistsEmail(String email);
}
