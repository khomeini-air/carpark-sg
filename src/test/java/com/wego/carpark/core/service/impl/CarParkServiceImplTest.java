package com.wego.carpark.core.service.impl;

import com.wego.carpark.core.dao.entity.CarParkAvailabilityEntity;
import com.wego.carpark.core.dao.entity.CarParkEntity;
import com.wego.carpark.core.dao.repository.CarParkRepository;
import com.wego.carpark.core.model.CarPark;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CarParkServiceImplTest {
    private final static Double LATITUDE = 1.0;
    private final static Double LONGITUDE = 3.0;
    private final static Integer PAGE = 0;
    private final static Integer PER_PAGE = 1;

    @MockBean
    private CarParkRepository carParkRepository;

    @Autowired
    private CarParkServiceImpl carParkServiceImpl;

    @Test
    public void testFindNearestAvailableWithNoResult() {
        when(carParkRepository.findAllAvailable(LATITUDE, LONGITUDE, PageRequest.of(PAGE,PER_PAGE))).thenReturn(Collections.emptyList());

        final List<CarPark> result = carParkServiceImpl.findNearestAvailable(LATITUDE, LONGITUDE, PAGE, PER_PAGE);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testFindNearestAvailableWithResult() {
        final CarParkEntity carParkEntity1 = new CarParkEntity();
        final CarParkEntity carParkEntity2 = new CarParkEntity();
        final CarParkAvailabilityEntity carParkAvailabilityEntity1 = new CarParkAvailabilityEntity();
        final CarParkAvailabilityEntity carParkAvailabilityEntity2 = new CarParkAvailabilityEntity();

        carParkEntity1.setId(1);
        carParkEntity1.setCarParkNo("AAA");
        carParkEntity1.setAvailability(carParkAvailabilityEntity1);
        carParkAvailabilityEntity1.setTotalLots(10);
        carParkAvailabilityEntity1.setAvailableLots(5);

        carParkEntity2.setId(2);
        carParkEntity2.setCarParkNo("BBB");
        carParkEntity2.setAvailability(carParkAvailabilityEntity2);
        carParkAvailabilityEntity2.setTotalLots(20);
        carParkAvailabilityEntity2.setAvailableLots(10);

        when(carParkRepository.findAllAvailable(LATITUDE, LONGITUDE, PageRequest.of(PAGE,PER_PAGE))).thenReturn(List.of(carParkEntity1, carParkEntity2));

        final CarPark carPark1 = CarPark.builder().code("AAA").totalLots(10).availableLots(5).build();
        final CarPark carPark2 = CarPark.builder().code("BBB").totalLots(20).availableLots(10).build();

        final List<CarPark> result = carParkServiceImpl.findNearestAvailable(LATITUDE, LONGITUDE, PAGE, PER_PAGE);
        assertNotNull(result);
        assertIterableEquals(List.of(carPark1, carPark2), result);
    }

}

