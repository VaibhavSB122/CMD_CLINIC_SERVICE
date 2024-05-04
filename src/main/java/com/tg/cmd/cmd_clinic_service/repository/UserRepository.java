package com.tg.cmd.cmd_clinic_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.cmd.cmd_clinic_service.model.User;



public interface UserRepository extends JpaRepository<User,String>{

}
