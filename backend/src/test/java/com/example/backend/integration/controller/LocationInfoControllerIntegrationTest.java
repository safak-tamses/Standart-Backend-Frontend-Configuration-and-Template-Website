package com.example.backend.integration.controller;

import com.example.backend.model.LocationInfo;
import com.example.backend.repository.LocationInfoRepository;
import com.example.backend.service.LocationInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationInfoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocationInfoService locationInfoService;

    @Autowired
    private LocationInfoRepository locationInfoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // delete all metoduyla veri tabanının silinmesi gerekiyor
        locationInfoRepository.deleteAll();
    }
    @AfterEach
    public void tearDown(){
        locationInfoRepository.deleteAll();
    }
    @Test
    public void testSaveData() throws Exception {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setCity("Istanbul");
        locationInfo.setDistrict("Kadikoy");

        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locationInfo)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void testShowAllInfo() throws Exception {
        // Veritabanına test verileri eklemek için
        LocationInfo location1 = new LocationInfo();
        location1.setCity("Istanbul");
        location1.setDistrict("Kadikoy");
        locationInfoService.saveInfo(location1);

        LocationInfo location2 = new LocationInfo();
        location2.setCity("Ankara");
        location2.setDistrict("Cankaya");
        locationInfoService.saveInfo(location2);

        mockMvc.perform(MockMvcRequestBuilders.get("/show")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value("Istanbul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].district").value("Kadikoy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].city").value("Ankara"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].district").value("Cankaya"));
    }

}
