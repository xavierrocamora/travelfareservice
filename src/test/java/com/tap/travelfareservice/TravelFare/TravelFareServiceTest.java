package com.tap.travelfareservice.TravelFare;

import com.tap.travelfareservice.domain.FareComparator;
import com.tap.travelfareservice.domain.TravelFare;
import com.tap.travelfareservice.domain.TravelFareData;
import com.tap.travelfareservice.exception.DriverNotFoundException;
import com.tap.travelfareservice.exception.ServerInternalException;
import com.tap.travelfareservice.repository.DriverRepository;
import com.tap.travelfareservice.repository.TravelFareRepository;
import com.tap.travelfareservice.service.TravelFareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TravelFareServiceTest {

    @Mock
    private TravelFareRepository travelFareRepository;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private FareComparator fareComparator;

    private TravelFareService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new TravelFareService(travelFareRepository, driverRepository, fareComparator);
    }

    @Test
    public void canGetTravelFareData() {
        // given

        // when
        underTest.getCheapestFare();

        // then
        verify(travelFareRepository).getTravelFareData();
    }

    @Test
    public void canSaveCheapestFare() {
        // given
        TravelFare travelFare = new TravelFare();
        given(travelFareRepository.getTravelFareData())
                .willReturn(new TravelFareData());
        given(fareComparator.calculateCheapestFare())
                .willReturn(travelFare);

        // when
        underTest.getCheapestFare();

        // then
        verify(travelFareRepository).saveCheapestTravelFare(travelFare);
    }

    @Test
    public void willThrowWhenCanNotReadFareData() {
        // given
        given(travelFareRepository.getTravelFareData())
                .willReturn(null);

        // when

        // then
        assertThatThrownBy(() -> underTest.getCheapestFare())
                .isInstanceOf(ServerInternalException.class)
                .hasMessageContaining("Could not get travel fare data.");

        verify(travelFareRepository, never()).saveCheapestTravelFare(new TravelFare());
    }

    @Test
    public void willThrowWhenCanNotSaveFareData() {
        // given
        given(travelFareRepository.getTravelFareData())
                .willReturn(new TravelFareData());
        given(travelFareRepository.saveCheapestTravelFare(new TravelFare()))
                .willReturn(false);

        // when

        // then
        assertThatThrownBy(() -> underTest.getCheapestFare())
                .isInstanceOf(ServerInternalException.class)
                .hasMessageContaining("Could not save cheapest travel fare.");

        verify(travelFareRepository, never()).saveCheapestTravelFare(new TravelFare());
    }
}
