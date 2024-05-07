package com.tg.cmd.cmd_clinic_service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.cmd.cmd_clinic_service.exception.BadChoiceException;
import com.tg.cmd.cmd_clinic_service.externalservice.DoctorServiceFactory;
import com.tg.cmd.cmd_clinic_service.externalservice.IDoctorService;
import com.tg.cmd.cmd_clinic_service.model.Clinic;
import com.tg.cmd.cmd_clinic_service.repository.ServiceRepository;
import com.tg.cmd.cmd_clinic_service.service.ServiceService;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

	private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

	@Autowired
	private ServiceRepository serviceRepository;

	private IDoctorService doctorService;
	public ServiceServiceImpl() throws BadChoiceException {
		// TODO Auto-generated constructor stub
    	doctorService = DoctorServiceFactory.create("mock");
	}
	
	@Override
	public List<Clinic> getAllServices() {
		try {
			logger.info("Retrieving all clinics from the database");
			// Retrieve all clinics from the database using the repository
			List<Clinic> clinics = serviceRepository.findAll();
			return clinics;
		} catch (Exception e) {
			logger.error("Error retrieving all clinics", e);
			// If an error occurs, log it and throw a runtime exception
			throw new RuntimeException("Error retrieving all clinics: " + e.getMessage());
		}
	}

	@Override
	public Clinic getServiceById(long id) {
		try {
			logger.info("Retrieving clinic with ID {} from the database", id);
			// Retrieve a clinic by its ID from the database using the repository
			Optional<Clinic> clinic = serviceRepository.findById(id);
			return clinic.orElse(null);
		} catch (Exception e) {
			logger.error("Error retrieving clinic with ID {}", id, e);
			// If an error occurs, log it and throw a runtime exception
			throw new RuntimeException("Error retrieving clinic with ID " + id + ": " + e.getMessage());
		}
	}

	@Override
	public Clinic createService(Clinic clinic) {
		try {
			logger.info("Creating new clinic: {}", clinic);
			// Perform validation checks
			if (clinic.getFullName() == null || clinic.getFullName().isEmpty()) {
				throw new IllegalArgumentException("Clinic name cannot be empty");
			}
			if (clinic.getPhoneNumber() == null || clinic.getPhoneNumber().isEmpty()) {
				throw new IllegalArgumentException("Phone number cannot be empty");
			}
			// Save the clinic if validation passes
			return serviceRepository.save(clinic);
		} catch (Exception e) {
			logger.error("Error creating clinic", e);
			// If an error occurs, log it and throw a runtime exception
			throw new RuntimeException("Error creating clinic: " + e.getMessage());
		}
	}

	@Override
	public Clinic updateService(long id, Clinic clinic) {
		try {
			logger.info("Updating clinic with ID {}: {}", id, clinic);
			// Retrieve the existing clinic from the database
			Optional<Clinic> existingClinicOptional = serviceRepository.findById(id);
			if (existingClinicOptional.isPresent()) {
				Clinic existingClinic = existingClinicOptional.get();
				// Perform validation checks
				if (clinic.getFullName() == null || clinic.getFullName().isEmpty()) {
					throw new IllegalArgumentException("Clinic name cannot be empty");
				}
				if (clinic.getPhoneNumber() == null || clinic.getPhoneNumber().isEmpty()) {
					throw new IllegalArgumentException("Phone number cannot be empty");
				}
				// Update clinic attributes
				// For example: existingClinic.setName(clinic.getName());
				return serviceRepository.save(existingClinic);
			} else {
				// If the clinic with the given ID is not found, throw an exception
				throw new IllegalArgumentException("Clinic with ID " + id + " not found");
			}
		} catch (Exception e) {
			logger.error("Error updating clinic with ID {}", id, e);
			// If an error occurs, log it and throw a runtime exception
			throw new RuntimeException("Error updating clinic with ID " + id + ": " + e.getMessage());
		}
	}

	@Override
	public void deleteService(long id) {
		try {
			logger.info("Deleting clinic with ID {}", id);
			// Delete the clinic from the database using the repository
			serviceRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Error deleting clinic with ID {}", id, e);
			// If an error occurs, log it and throw a runtime exception
			throw new RuntimeException("Error deleting clinic with ID " + id + ": " + e.getMessage());
		}
	}
	
	protected boolean setBloodTestScheduled(Clinic clinic) {
        logger.info("Checking doctor's availability for blood test...");
	    return doctorService.checkBloodTestAndInactiveService(clinic);
	}


}