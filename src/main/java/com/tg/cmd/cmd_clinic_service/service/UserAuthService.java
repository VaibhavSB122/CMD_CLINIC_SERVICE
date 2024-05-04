package com.tg.cmd.cmd_clinic_service.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tg.cmd.cmd_clinic_service.model.Role;
import com.tg.cmd.cmd_clinic_service.model.User;






@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found.");
		}
		List<Role> roles = userService.getRoles(username);
		List<GrantedAuthority> grantedAuthorities = roles.stream().map(r -> {
			return new SimpleGrantedAuthority(r.getRoleName());
		}).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);
	}

	public User getUserByUsername(String username) {
		User user= userService.getUserByName(username);

		if (user != null) {

			return user;
		}

		return null;
	}

	
}
