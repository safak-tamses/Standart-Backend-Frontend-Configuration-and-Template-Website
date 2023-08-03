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

    public LocationInfo saveInfo(LocationInfo locationInfo) {
        try {
            return locationInfoRepository.save(locationInfo);
        } catch (Exception e) {
            throw new LocationInfoException("Error while saving info: " + e.getMessage());
        }
    }

    public List<LocationInfo> viewAllInfo(){
        try {
            return locationInfoRepository.findAll();
        } catch (Exception e) {
            throw new ViewAllInfoException("Error while fetching all info", e);
        }
    }

}
