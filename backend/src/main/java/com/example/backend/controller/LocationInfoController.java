package com.example.backend.controller;

import com.example.backend.model.LocationInfo;
import com.example.backend.service.LocationInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping()
public class LocationInfoController {
    private final LocationInfoService locationInfoService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LocationInfo> saveData(@RequestBody LocationInfo locationInfo){
        return new ResponseEntity<>(locationInfoService.saveInfo(locationInfo), HttpStatus.CREATED);
    }

    @GetMapping("/show")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LocationInfo>> showAllInfo(){
        return new ResponseEntity<>(locationInfoService.viewAllInfo(),HttpStatus.OK);
    }
}
