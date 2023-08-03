package com.example.backend.unit.controller;

import com.example.backend.controller.LocationInfoController;
import com.example.backend.model.LocationInfo;
import com.example.backend.service.LocationInfoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationInfoControllerUnitTest {
    @Mock
    private LocationInfoService locationInfoService;

    @InjectMocks
    private LocationInfoController locationInfoController;

    @Test
    public void LocationInfoController_saveData_Test_LocationInfoSavedSuccessfully() {
        LocationInfo locationInfo = LocationInfo.builder()
                .city("İstanbul")
                .district("Kartal")
                .build();

        // Mock locationInfoService.saveInfo() method to return the provided locationInfo
        when(locationInfoService.saveInfo(locationInfo)).thenReturn(locationInfo);

        ResponseEntity<LocationInfo> responseEntity = locationInfoController.saveData(locationInfo);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(locationInfo);
    }
    @Test
    public void LocationInfoController_showAllInfo_Test_FetchAllLocationInfoSuccessfully() {
        LocationInfo locationInfo1 = LocationInfo.builder()
                .city("İstanbul")
                .district("Kartal")
                .build();

        LocationInfo locationInfo2 = LocationInfo.builder()
                .city("Ankara")
                .district("Kızılay")
                .build();

        List<LocationInfo> locationInfoList = Arrays.asList(locationInfo1, locationInfo2);

        // Mock locationInfoService.viewAllInfo() method to return the locationInfoList
        when(locationInfoService.viewAllInfo()).thenReturn(locationInfoList);

        ResponseEntity<List<LocationInfo>> responseEntity = locationInfoController.showAllInfo();

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(locationInfoList);
    }
}
