package com.tg.cmd.cmd_clinic_service.externalservice;

import java.time.LocalDate;

import com.tg.cmd.cmd_clinic_service.model.Clinic;
import com.tg.cmd.cmd_clinic_service.model.Doctor;

public class DoctorServiceMockImpl implements IDoctorService {

	
	 // Instance of Doctor class for mock implementation
    private Doctor doctor = new Doctor();
    
    @Override
    public boolean checkBloodTestAndInactiveService(Clinic clinic) {
    	 // Mock implementation to set doctor availability status
        doctor.setStatus(true);

        // Return the doctor availability status
        return doctor.isStatus();

    
}
}