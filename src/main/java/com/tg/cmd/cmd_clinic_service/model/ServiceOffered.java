package com.tg.cmd.cmd_clinic_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ServiceOffered {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private long serviceId;

    @Column(nullable = false)
    private String serviceName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "service_offered")
    private ServiceType service;

    @Column(nullable = false, unique = true)
    private String serviceCode;

    @Column(nullable = false)
    private String serviceDescription;

    @Column(nullable = false)
    private double averagePrice;

    @Column(nullable = false)
    private boolean isActive;

	@ManyToOne
    @JoinColumn(name = "clinic_id") // Foreign key to Clinic
    private Clinic clinic; // Clinic associated with this schedule
}