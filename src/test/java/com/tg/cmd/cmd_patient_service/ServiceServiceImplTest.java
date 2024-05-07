package com.tg.cmd.cmd_patient_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tg.cmd.cmd_clinic_service.model.Clinic;
import com.tg.cmd.cmd_clinic_service.repository.ServiceRepository;
import com.tg.cmd.cmd_clinic_service.service.impl.ServiceServiceImpl;

@ExtendWith(MockitoExtension.class)
class ServiceServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private Clinic clinic;

    @BeforeEach
    void setUp() {
        clinic = new Clinic();
        clinic.setId(1L);
        clinic.setFullName("Test Clinic");
        clinic.setPhoneNumber("1234567890");
    }

    @Test
    void testGetAllServices() {
        List<Clinic> clinicList = new ArrayList<>();
        clinicList.add(clinic);
        when(serviceRepository.findAll()).thenReturn(clinicList);
        
        List<Clinic> result = serviceService.getAllServices();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clinic, result.get(0));
    }

    @Test
    void testGetServiceById() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(clinic));
        
        Clinic result = serviceService.getServiceById(1L);
        
        assertNotNull(result);
        assertEquals(clinic, result);
    }

    @Test
    void testCreateService() {
        when(serviceRepository.save(clinic)).thenReturn(clinic);
        
        Clinic result = serviceService.createService(clinic);
        
        assertNotNull(result);
        assertEquals(clinic, result);
    }

    @Test
    void testUpdateService() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(clinic));
        when(serviceRepository.save(clinic)).thenReturn(clinic);
        
        Clinic result = serviceService.updateService(1L, clinic);
        
        assertNotNull(result);
        assertEquals(clinic, result);
    }

    @Test
    void testDeleteService() {
        assertDoesNotThrow(() -> serviceService.deleteService(1L));
    }

    @Test
    void testGetAllServices_Positive() {
        List<Clinic> clinicList = new ArrayList<>();
        clinicList.add(clinic);
        when(serviceRepository.findAll()).thenReturn(clinicList);
        
        List<Clinic> result = serviceService.getAllServices();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clinic, result.get(0));
    }

    @Test
    void testGetServiceById_Positive() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(clinic));
        
        Clinic result = serviceService.getServiceById(1L);
        
        assertNotNull(result);
        assertEquals(clinic, result);
    }



    @Test
    void testUpdateService_Positive() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(clinic));
        when(serviceRepository.save(clinic)).thenReturn(clinic);
        
        Clinic result = serviceService.updateService(1L, clinic);
        
        assertNotNull(result);
        assertEquals(clinic, result);
    }

//  

    @Test
    void testDeleteService_Positive() {
        assertDoesNotThrow(() -> serviceService.deleteService(1L));
    }
}
