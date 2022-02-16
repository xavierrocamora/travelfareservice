package com.tap.travelfareservice.Driver;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.VehicleType;
import com.tap.travelfareservice.repository.DriverRepository;
import com.tap.travelfareservice.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DriverServiceTest {

    // In this unitary test we want to test the service in a completely
    // independent way of our repository, which we know already works
    // as proven by the repository unit test.
    // Therefore we use a mock of the repository
    @Mock private DriverRepository driverRepository;
    private DriverService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DriverService(driverRepository);
    }

    @Test
    void canAddDriver() {
        // given
        String email = "hawkings@gmail";
        Driver driver = new Driver(
                "John",
                "Hawkings",
                email,
                VehicleType.CAR,
                2.0,
                100.0);

        // when
        underTest.addDriver(driver);

        // then
        ArgumentCaptor<Driver> driverArgumentCaptor =
                ArgumentCaptor.forClass(Driver.class);

        // we verify the save method is being called and capture the driver
        // being passed as argument
        verify(driverRepository)
                .save(driverArgumentCaptor.capture());

        // in order to compare it with our driver
        Driver capturedDriver = driverArgumentCaptor.getValue();

        assertThat(capturedDriver).isEqualTo(driver);


    }

    @Test
    void canGetAllDrivers() {
        // given
        underTest.getAllDrivers();
        // then
        verify(driverRepository).findAll();
    }

    @Test
    void canDeleteDriver() {
        // given
        long id = 10;
        // by default our mock will give false
        // as response to existsById
        // we force a true
        given(driverRepository.existsById(id)).willReturn(true);
        // when
        underTest.deleteDriver(id);
        // then
        verify(driverRepository).deleteById(id);

    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        Driver driver = new Driver(
                "John",
                "Hawkings",
                "hawkings@gmail",
                VehicleType.CAR,
                2.0,
                100.0);

        given(driverRepository.selectExistsEmail(anyString()))
                .willReturn(true);

        // when

        // then
        assertThatThrownBy( () -> underTest.addDriver(driver))
                .isInstanceOf(HttpClientErrorException.BadRequest.class)
                .hasMessageContaining("Email " + driver.getEmail() + " taken");

        // if an exception is thrown we must verify nothing is saved
        verify(driverRepository, never()).save(any());
    }
}