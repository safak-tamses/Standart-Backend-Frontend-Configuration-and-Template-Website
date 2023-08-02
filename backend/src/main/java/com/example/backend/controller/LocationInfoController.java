package com.example.backend.controller;

import com.example.backend.model.LocationInfo;
import com.example.backend.service.LocationInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping()
public class LocationInfoController {
    private final LocationInfoService locationInfoService;

    @PostMapping("/save")
    public ResponseEntity saveData(@RequestBody LocationInfo locationInfo){
        return locationInfoService.saveInfo(locationInfo);
    }

    @GetMapping("/show")
    public ResponseEntity<List<LocationInfo>> showAllInfo(){
        return locationInfoService.viewAllInfo();
    }
}
