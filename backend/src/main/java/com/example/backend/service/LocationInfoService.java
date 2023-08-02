package com.example.backend.service;

import com.example.backend.exception.LocationInfoException;
import com.example.backend.exception.ViewAllInfoException;
import com.example.backend.model.LocationInfo;
import com.example.backend.repository.LocationInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationInfoService {
    private final LocationInfoRepository locationInfoRepository;

    public ResponseEntity saveInfo(LocationInfo locationInfo) {
        try {
            locationInfoRepository.save(locationInfo);
            return ResponseEntity.ok("Info saved successfully");
        } catch (Exception e) {
            throw new LocationInfoException("Error while saving info: " + e.getMessage());
        }
    }

    public ResponseEntity<List<LocationInfo>> viewAllInfo(){
        try {
            List<LocationInfo> allLocationInfo = locationInfoRepository.findAll();
            return ResponseEntity.ok(allLocationInfo);
        } catch (Exception e) {
            throw new ViewAllInfoException("Error while fetching all info", e);
        }
    }

}
