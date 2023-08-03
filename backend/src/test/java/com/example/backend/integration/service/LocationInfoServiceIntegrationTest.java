package com.example.backend.integration.service;

import com.example.backend.exception.LocationInfoException;
import com.example.backend.exception.ViewAllInfoException;
import com.example.backend.model.LocationInfo;
import com.example.backend.repository.LocationInfoRepository;
import com.example.backend.service.LocationInfoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class LocationInfoServiceIntegrationTest {

    @Autowired
    private LocationInfoService locationInfoService;

    @Autowired
    private LocationInfoRepository locationInfoRepository;

    @BeforeEach
    public void setUp() {
        // Test verilerini hazırlamak için kullanılabilir
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
    public void tearDown() {
        // Test sonrası temizlik yapmak için kullanılabilir
        locationInfoRepository.deleteAll();
    }

    @Test
    public void testSaveInfo() {
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
    public void testViewAllInfo() {
        List<LocationInfo> locations = locationInfoService.viewAllInfo();

        assertEquals(2, locations.size());
    }


    /* Bu kısımda exceptionların test edilmesi gerekiyor
     fakat jpa repository classlarının hazır methodlarının exception
     fırlatması için veri tabanının durdurulması gerekiyor */


//    @Test
//    public void testViewAllInfo_Exception() {
//        // Veritabanı geçici olarak devre dışı bırakılarak exception fırlatma senaryosu test ediliyor.
//        locationInfoRepository.deleteAll();
//
//        assertThrows(ViewAllInfoException.class, () -> {
//            locationInfoService.viewAllInfo();
//        });
//    }
//
//    @Test
//    public void testSaveInfo_LocationInfoException() {
//        // Veritabanı geçici olarak devre dışı bırakılarak exception fırlatma senaryosu test ediliyor.
//        // zaten veri tabanına before tagıyla veri eklediğimiz için id si 1 olan veriyi kaydetmek exception fırlatır
//
//        LocationInfo location = LocationInfo.builder()
//                .city("İstanbul")
//                .district("Maltepe").build();
//        assertThrows(LocationInfoException.class, () -> {
//            locationInfoService.saveInfo(location);
//        });
//    }


}
