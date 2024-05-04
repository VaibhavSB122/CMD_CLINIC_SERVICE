package com.tg.cmd.cmd_clinic_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.cmd.cmd_clinic_service.model.Clinic;

public interface ServiceRepository extends JpaRepository<Clinic, Long> {

}