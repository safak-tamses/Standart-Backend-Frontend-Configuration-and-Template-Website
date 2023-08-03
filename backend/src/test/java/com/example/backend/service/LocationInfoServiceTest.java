package com.example.backend.service;

import com.example.backend.exception.LocationInfoException;
import com.example.backend.exception.ViewAllInfoException;
import com.example.backend.model.LocationInfo;
import com.example.backend.repository.LocationInfoRepository;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationInfoServiceTest {

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
        Assertions.assertNotNull(savedLocationInfo);
        Assertions.assertEquals(savedLocationInfo.getCity(), "İstanbul");
        Assertions.assertEquals(savedLocationInfo.getDistrict(), "Kartal");

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

        Assertions.assertNotNull(fetchedLocationInfoList);
        Assertions.assertEquals(fetchedLocationInfoList.size(), 2);
        Assertions.assertIterableEquals(fetchedLocationInfoList, List.of(locationInfo1, locationInfo2));

    }

    @Test
    public void testViewAllInfo_FetchError() {
        // Given
        String errorMessage = "Database connection error";
        when(locationInfoRepository.findAll()).thenThrow(new RuntimeException(errorMessage));

        // When, Then
        Assertions.assertThrows(ViewAllInfoException.class, () -> locationInfoService.viewAllInfo(), "Error while fetching all info");
        verify(locationInfoRepository, times(1)).findAll();

    }

    @Test
    public void testSaveInfo_SaveError() {
        // Given
        LocationInfo locationInfo = new LocationInfo(/* Initialize LocationInfo object */);
        String errorMessage = "Database connection error";
        when(locationInfoRepository.save(locationInfo)).thenThrow(new RuntimeException(errorMessage));

        // When, Then
        Assertions.assertThrows(LocationInfoException.class, () -> locationInfoService.saveInfo(locationInfo), "Error while saving info: " + errorMessage);
        verify(locationInfoRepository, times(1)).save(locationInfo);
    }
}