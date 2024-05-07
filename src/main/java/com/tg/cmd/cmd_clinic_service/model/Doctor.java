package com.tg.cmd.cmd_clinic_service.model;

import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {

    private long id;

    private String name;

    private String specialization;

    private String phoneNumber;

    private String email;
    
    private String isActive;

    private String isInactive;
    
    private String username;
    
    private String password;
    
    private boolean status;
    
    
    private Set<Clinic> clinics = new HashSet<>();

	
}
	

