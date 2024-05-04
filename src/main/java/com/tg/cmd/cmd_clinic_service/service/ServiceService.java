package com.tg.cmd.cmd_clinic_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tg.cmd.cmd_clinic_service.model.Clinic;

public interface ServiceService {

	// Get all services
	List<Clinic> getAllServices();

	// Get service by ID
	Clinic getServiceById(long id);

	// Create a new service
	Clinic createService(Clinic clinic);

	// Method to update an existing service
	Clinic updateService(long id, Clinic clinic);

	void deleteService(long id);

}