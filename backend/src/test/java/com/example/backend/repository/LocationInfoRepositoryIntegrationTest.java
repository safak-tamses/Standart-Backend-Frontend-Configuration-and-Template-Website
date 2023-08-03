package com.example.backend.repository;

import com.example.backend.model.LocationInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationInfoRepositoryIntegrationTest {
    @Autowired
    private LocationInfoRepository locationInfoRepository;


    @BeforeEach
    public void setUp() {
        LocationInfo location1 = LocationInfo.builder()
                .city("Ankara")
                .district("Beypazarı")
                .build();
        LocationInfo location2 = LocationInfo.builder()
                .city("Ankara")
                .district("Mamak")
                .build();
        locationInfoRepository.save(location1);
        locationInfoRepository.save(location2);
    }

    @AfterEach
    public void tearDown() {
        locationInfoRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        List<LocationInfo> locations = locationInfoRepository.findAll();
        assertEquals( 2, locations.size());
    }

    @Test
    public void testSaveLocation() {
        LocationInfo location = LocationInfo.builder()
                .city("İstanbul")
                .district("Zeytinburnu")
                .build();
        locationInfoRepository.save(location);

        List<LocationInfo> allLocations = locationInfoRepository.findAll();
        assertTrue(allLocations.contains(location));
    }
}