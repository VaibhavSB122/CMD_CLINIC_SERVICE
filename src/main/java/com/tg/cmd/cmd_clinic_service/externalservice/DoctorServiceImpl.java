package com.tg.cmd.cmd_clinic_service.externalservice;

import com.tg.cmd.cmd_clinic_service.model.Clinic;
import com.tg.cmd.cmd_clinic_service.model.Doctor;
import com.tg.cmd.cmd_clinic_service.repository.ServiceRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean checkBloodTestAndInactiveService(Clinic clinic) {
        // Call the doctor API to get doctor availability
        ResponseEntity<String> doctorResponse = restTemplate.exchange("doctorApiUrl" + clinic.getDoctorId(),
                HttpMethod.GET, null, String.class);

        // Log the response from the doctor API
        log.info("Response: {}", doctorResponse.getBody());

        // Extract information from the response body and determine if blood test is scheduled
        boolean bloodTestScheduled = extractBloodTestScheduled(doctorResponse.getBody());

        // Inactivate the service if blood test is scheduled and service is active
        if (bloodTestScheduled && clinic.isServiceActive()) {
            clinic.setServiceActive(false);
            serviceRepository.save(clinic); // Assuming this saves the updated clinic to the database
        }

        return bloodTestScheduled;
    }

    // Extract information from the response body and determine if blood test is scheduled
    private boolean extractBloodTestScheduled(String responseBody) {
        // For demonstration purposes, always returning a default value of true
        // You should implement logic to extract actual information from the response body
        return true;
    }
}
