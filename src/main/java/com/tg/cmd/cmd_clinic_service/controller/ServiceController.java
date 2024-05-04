package com.tg.cmd.cmd_clinic_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tg.cmd.cmd_clinic_service.model.Clinic;
import com.tg.cmd.cmd_clinic_service.service.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ServiceService serviceService;

    // Retrieve all services
    @GetMapping("/get-all-service")
    public ResponseEntity<List<Clinic>> getAllServices() {
        try {
            logger.info("Retrieving all clinics");
            List<Clinic> clinics = serviceService.getAllServices();
            return ResponseEntity.ok(clinics);
        } catch (Exception e) {
            logger.error("Error retrieving clinics", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Retrieve a service by its ID
    @GetMapping("/service/{id}")
    public ResponseEntity<Clinic> getServiceById(@PathVariable Long id) {
        try {
            logger.info("Retrieving clinic with ID {}", id);
            Clinic clinic = serviceService.getServiceById(id);
            if (clinic != null) {
                return ResponseEntity.ok(clinic);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error retrieving clinic with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Create a new service
    @PostMapping("/create-service")
    public ResponseEntity<String> createService(@RequestBody Clinic clinic) {
        try {
            logger.info("Creating new clinic: {}", clinic);
            Clinic createdClinic = serviceService.createService(clinic);
            logger.info("New clinic created: {}", createdClinic);
            return ResponseEntity.ok("Clinic created successfully");
        } catch (Exception e) {
            logger.error("Error creating clinic", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating clinic: " + e.getMessage());
        }
    }

    // Update an existing service
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateService(@PathVariable Long id, @RequestBody Clinic clinic) {
        try {
            logger.info("Updating clinic with ID {}: {}", id, clinic);
            Clinic updatedClinic = serviceService.updateService(id, clinic);
            logger.info("Clinic updated: {}", updatedClinic);
            return ResponseEntity.ok("Clinic updated successfully");
        } catch (Exception e) {
            logger.error("Error updating clinic with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating clinic: " + e.getMessage());
        }
    }

    // Delete a service
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        try {
            logger.info("Deleting clinic with ID {}", id);
            serviceService.deleteService(id);
            logger.info("Clinic with ID {} deleted successfully", id);
            return ResponseEntity.ok("Clinic deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting clinic with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting clinic: " + e.getMessage());
        }
    }
}