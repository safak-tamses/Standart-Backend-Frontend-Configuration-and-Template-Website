package com.example.backend.repository;

import com.example.backend.model.LocationInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LocationInfoRepositoryTests {
    @Autowired
    private LocationInfoRepository locationInfoRepository;

    @Test
    public void LocationInfoRepository_Save_ReturnSavedLocationInfo() {
        //Arrange
        LocationInfo locationInfo = LocationInfo.builder()
                .city("İstanbul")
                .district("Pendik")
                .build();
        //Act
        LocationInfo savedLocationInfo = locationInfoRepository.save(locationInfo);
        //Assert
        Assertions.assertThat(savedLocationInfo).isNotNull();
        Assertions.assertThat(savedLocationInfo.getId()).isGreaterThan(0);
    }

    @Test
    public void LocationInfoRepository_GetAll_ReturnMoreThenOneLocationInfo() {
        //Arrange
        LocationInfo locationInfo = LocationInfo.builder()
                .city("İstanbul")
                .district("Pendik")
                .build();
        LocationInfo locationInfo2 = LocationInfo.builder()
                .city("İzmir")
                .district("Bornova")
                .build();
        //Act
        int beforeSize = locationInfoRepository.findAll().size();
        locationInfoRepository.save(locationInfo);
        locationInfoRepository.save(locationInfo2);

        List<LocationInfo> locationInfoList = locationInfoRepository.findAll();
        //Assert
        Assertions.assertThat(locationInfoList).isNotNull();
        Assertions.assertThat(locationInfoList.size()).isEqualTo(beforeSize + 2);
    }
}
