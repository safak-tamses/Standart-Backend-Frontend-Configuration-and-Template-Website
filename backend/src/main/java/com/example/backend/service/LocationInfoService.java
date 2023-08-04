package com.example.backend.service;

import com.example.backend.exception.LocationInfoException;
import com.example.backend.exception.ViewAllInfoException;
import com.example.backend.model.LocationInfo;
import com.example.backend.repository.LocationInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationInfoService {
    private final LocationInfoRepository locationInfoRepository;

    public LocationInfo saveInfo(LocationInfo locationInfo) {
        try {
            locationInfo.setCity(locationInfo.getCity().toUpperCase());
            LocationInfo testLocationInfo = locationInfoRepository.findByCityAndDistrict(locationInfo.getCity(),locationInfo.getDistrict());


//            List<LocationInfo> locationInfoList = locationInfoRepository.findAll();
//            List<LocationInfo> locationInfos=locationInfoList.stream()
//                            .filter(locationInfo1 -> locationInfo1.getCity().equals(locationInfo.getCity())
//                                    && locationInfo1.getDistrict().equals(locationInfo.getDistrict()))
//                                    .toList();
            if (testLocationInfo != null){
                throw new LocationInfoException("Already have it");
            }
            else{

                return locationInfoRepository.save(locationInfo);
            }


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

    //filtre kısmına gerekli filtreler girilebilir
//    public List<LocationInfo> filter(){
//        List<LocationInfo> locationInfoList = locationInfoRepository.findAll().stream()
//                .filter(locationInfo -> locationInfo.getCity().equals("örnek şehir filtresi")
//                        && locationInfo.getDistrict().equals("örnek semt filtresi")
//                        && locationInfo.getId() >= 3
//                )
//                .collect(Collectors.toList());
//        return locationInfoList;
//    }

    //stream.map yapısının örnek kullanımı  inline ve lambda edilebilir daha sonra
//    public List<String> returnListsCity(){
//        List<String> locationInfoCityList = locationInfoRepository.findAll().stream()
//                .map(locationInfo -> locationInfo.getCity())
//                .collect(Collectors.toList());
//        return locationInfoCityList;
//    }

}
