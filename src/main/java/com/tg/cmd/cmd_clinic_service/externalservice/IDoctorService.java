package com.tg.cmd.cmd_clinic_service.externalservice;

import com.tg.cmd.cmd_clinic_service.model.Clinic;

public interface IDoctorService {

    boolean checkBloodTestAndInactiveService(Clinic clinic);
}
