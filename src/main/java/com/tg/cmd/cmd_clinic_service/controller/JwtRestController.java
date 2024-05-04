package com.tg.cmd.cmd_clinic_service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tg.cmd.cmd_clinic_service.config.JwtUtil;
import com.tg.cmd.cmd_clinic_service.dto.JwtRequest;
import com.tg.cmd.cmd_clinic_service.dto.JwtResponse;
import com.tg.cmd.cmd_clinic_service.exception.DisabledUserException;
import com.tg.cmd.cmd_clinic_service.exception.InvalidUserCredentialsException;
import com.tg.cmd.cmd_clinic_service.model.Role;
import com.tg.cmd.cmd_clinic_service.model.User;
import com.tg.cmd.cmd_clinic_service.service.UserAuthService;
import com.tg.cmd.cmd_clinic_service.service.UserService;







@RestController
public class JwtRestController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserAuthService userAuthService;
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value="/signin",produces = {"application/json"})
	public ResponseEntity<JwtResponse> generateJwtToken(@RequestBody JwtRequest jwtRequest) {
		
		System.out.println(jwtRequest.getUserName()+""+jwtRequest.getUserPwd());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getUserPwd()));
	
		} catch (DisabledException e) {
			throw new DisabledUserException("User Inactive");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid Credentials");
		}
		UserDetails userDetails = userAuthService.loadUserByUsername(jwtRequest.getUserName());
		String username = userDetails.getUsername();
		String userpwd = userDetails.getPassword();
		List<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
				.collect(Collectors.toList());
		
		
		User user = new User();
		user.setUserName(username);
		user.setPassword(userpwd);
		List<Role> roleList = new ArrayList(roles);
		user.setRoles(roleList);
		String token = jwtUtil.generateToken(user);
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}

	@PostMapping(value="/signup",produces = {"application/json"})
	public ResponseEntity<String> signup(@RequestBody User user) {
		System.out.print(user.getUserName());
		System.out.print(user.getPassword());
		System.out.print(user.getRoles());
		User userObj = userAuthService.getUserByUsername(user.getUserName());

		if (userObj == null) {
			userService.saveUser(user);
			return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
		}
	}

}
