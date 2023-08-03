package com.example.backend.unit.service;

import com.example.backend.model.LocationInfo;
import com.example.backend.repository.LocationInfoRepository;
import com.example.backend.service.LocationInfoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationInfoServiceUnitTests {
    @Mock
    private LocationInfoRepository locationInfoRepository;

    @InjectMocks
    private LocationInfoService locationInfoService;
    @Test
    public void LocationInfoService_saveInfo_Test_LocationInfoSavedSuccessfully(){
        //Arrange
        LocationInfo locationInfo = LocationInfo.builder()
                .city("İstanbul")
                .district("Kartal")
                .build();
        //Act
        when(locationInfoRepository.save(Mockito.any(LocationInfo.class))).thenReturn(locationInfo);

        LocationInfo savedLocationInfo  = locationInfoService.saveInfo(locationInfo);

        //Assert
        Assertions.assertThat(savedLocationInfo).isNotNull();
        Assertions.assertThat(savedLocationInfo.getCity()).isEqualTo("İstanbul");
        Assertions.assertThat(savedLocationInfo.getDistrict()).isEqualTo("Kartal");

    }
    @Test
    public void LocationInfoService_viewAllInfo_Test_FetchAllLocationInfoSuccessfully() {
        LocationInfo locationInfo1 = LocationInfo.builder()
                .city("İstanbul")
                .district("Kartal")
                .build();

        LocationInfo locationInfo2 = LocationInfo.builder()
                .city("Ankara")
                .district("Kızılay")
                .build();

        List<LocationInfo> locationInfoList = Arrays.asList(locationInfo1, locationInfo2);

        when(locationInfoRepository.findAll()).thenReturn(locationInfoList);

        List<LocationInfo> fetchedLocationInfoList = locationInfoService.viewAllInfo();

        Assertions.assertThat(fetchedLocationInfoList).isNotNull();
        Assertions.assertThat(fetchedLocationInfoList).hasSize(2);
        Assertions.assertThat(fetchedLocationInfoList).contains(locationInfo1, locationInfo2);

    }
}
