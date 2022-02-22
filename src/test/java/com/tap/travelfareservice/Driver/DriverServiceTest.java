package com.tap.travelfareservice.Driver;

import com.tap.travelfareservice.domain.Driver;
import com.tap.travelfareservice.domain.VehicleType;
import com.tap.travelfareservice.exception.BadRequestException;
import com.tap.travelfareservice.exception.DriverNotFoundException;
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
    // Therefore, we use repository mock
    @Mock private DriverRepository driverRepository;
    private DriverService underTest;

    private static  final Driver driver = new Driver(
            "John",
            "Hawkings",
            "hawkings@gmail.com",
            VehicleType.TAXI,
            300.0,
            250.0);

    @BeforeEach
    public void setUp() {
        underTest = new DriverService(driverRepository);
    }

    @Test
    public void canAddDriver() {
        // given

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
    public void canGetAllDrivers() {
        // given
        underTest.getAllDrivers();
        // then
        verify(driverRepository).findAll();
    }

    @Test
    public void canDeleteDriver() {
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
    public void willThrowWhenEmailIsTaken() {
        // given

        given(driverRepository.selectExistsEmail(anyString()))
                .willReturn(true);

        // when

        // then
        assertThatThrownBy( () -> underTest.addDriver(driver))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + driver.getEmail() + " taken");

        // if an exception is thrown we must verify nothing is saved
        verify(driverRepository, never()).save(any());
    }

    @Test
    public void willThrowWhenDeleteDriverNotFound() {
        // given
        long id = 10;
        given(driverRepository.existsById(id))
                .willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> underTest.deleteDriver(id))
                .isInstanceOf(DriverNotFoundException.class)
                .hasMessageContaining("Driver with id " + id + " does not exist");

        verify(driverRepository, never()).deleteById(any());
    }

    @Test
    public void canGetDriverById() {
        // given
        long id = 10;

        given(driverRepository.findDriverById(id)).willReturn(java.util.Optional.of(driver));

        //when
        underTest.getDriverById(id);

        // then
        ArgumentCaptor<Long> driverIdArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);

        // we verify the save method is being called and capture the driver id
        // being passed as argument
        verify(driverRepository)
                .findDriverById(driverIdArgumentCaptor.capture());

        // in order to compare it with our driver id
        Long capturedDriverId = driverIdArgumentCaptor.getValue();

        assertThat(capturedDriverId).isEqualTo(id);
    }
}