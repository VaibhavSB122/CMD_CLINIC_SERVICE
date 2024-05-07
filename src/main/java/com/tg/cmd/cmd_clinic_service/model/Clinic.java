package com.tg.cmd.cmd_clinic_service.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    // Doctor ID
    @Column(name = "doctor_id")
    private String doctorId;
    

    // Full name of the clinic (e.g., Clinic XYZ)
    @Column
    private String fullName;
    
    // Phone number of the clinic
    @Column(name = "phone_number")
    private String phoneNumber;

    // Email address of the clinic
    @Column(name = "email")
    private String email;

    // Opening hours of the clinic
    @Column(name = "opening_hours")
    private LocalTime openingHours;
    
    // Closing hours of the clinic
    @Column(name = "closing_hours")
    private LocalTime closingHours;
    
    // Location of the clinic
    @Column(name = "loction", length = 1000)
    private String location;

    // Street address of the clinic
    @Column(name = "clinic_StreetAddress")
    private String streetAddress;

    // City of the clinic
    @Column(name = "clinic_city")
    private String city;

    // State of the clinic
    @Column(name = "clinic_state")
    private String state;

    // Country of the clinic
    @Column(name = "clinic_country")
    private String country;

    // Zip code of the clinic
    @Column(name = "clinic_zip_code")
    private String zipCode;

    // Date and time when the clinic was created
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    
    // Flag indicating whether the clinic is active
    @Column(name = "is_ServiceActive")
    private boolean isServiceActive;
    
    @Column(name = "set_ServiceActive")
    private boolean setServiceActive;
    
	// List of services offered by the clinic
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<ServiceOffered> servicesOffered;

	
    
}