package com.example.backend.service;

import com.example.backend.model.LocationInfo;
import com.example.backend.repository.LocationInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LocationInfoServiceIntegrationTest {
    @Autowired
    private LocationInfoService locationInfoService;

    @Autowired
    private LocationInfoRepository locationInfoRepository;

    @BeforeEach
    void setUp() {
        LocationInfo location1 = LocationInfo.builder()
                .city("İstanbul")
                .district("Beykoz")
                .build();
        LocationInfo location2 = LocationInfo.builder()
                .city("İstanbul")
                .district("Beyoğlu")
                .build();
        locationInfoRepository.save(location1);
        locationInfoRepository.save(location2);
    }

    @AfterEach
    void tearDown() {
        locationInfoRepository.deleteAll();
    }

    @Test
    void saveInfo() {
        LocationInfo location = LocationInfo.builder()
                .city("İstanbul")
                .district("Maltepe")
                .build();
        LocationInfo savedLocation = locationInfoService.saveInfo(location);

        assertNotNull(savedLocation.getId());
        assertEquals("İstanbul", savedLocation.getCity());
        assertEquals("Maltepe", savedLocation.getDistrict());
    }

    @Test
    void viewAllInfo() {
        List<LocationInfo> locations = locationInfoService.viewAllInfo();

        assertEquals(2, locations.size());
    }

    //try catch yapılarındaki fırlatılan exceptionlar için test yazılması lazım
}