package com.example.backend.repository;

import com.example.backend.model.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationInfoRepository extends JpaRepository<LocationInfo, Long> {
    LocationInfo findByCityAndDistrict (String cityName, String districtName);
}
