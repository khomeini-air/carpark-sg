package com.wego.carpark.integration.sg.gov.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DataServiceImplTest {
    @Autowired
    private DataServiceImpl dataServiceImpl;

    /**
     * Method under test: {@link DataServiceImpl#getCarParkAvailability()}
     */
    @Test
    public void testGetCarParkAvailability() {

        dataServiceImpl.getCarParkAvailability();
    }
}

