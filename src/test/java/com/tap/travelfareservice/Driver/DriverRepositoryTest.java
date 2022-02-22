package com.tap.travelfareservice.Driver;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.VehicleType;
import com.tap.travelfareservice.repository.DriverRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class DriverRepositoryTest {

    @Autowired
    private DriverRepository undertTest;

    @AfterEach
    void tearDown() {
        undertTest.deleteAll();
    }

    @Test
    void itShouldCheckIfDriverEmailExists() {
        //given
        String email = "hawkings@gmail";
        Driver driver = new Driver(
                "John",
                "Hawkings",
                email,
                VehicleType.TAXI,
                2.0,
                100.0);
        undertTest.save(driver);

        // when
        boolean expected = undertTest.selectExistsEmail(email);

        // then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckWhenDriverEmailDoesNotExists() {
        // given
        String email = "markus@gmail.com";

        // when
        boolean expected = undertTest.selectExistsEmail(email);

        // then
        assertThat(expected).isFalse();
    }
}
