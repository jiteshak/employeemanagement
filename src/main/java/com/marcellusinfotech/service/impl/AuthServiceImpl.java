package com.marcellusinfotech.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcellusinfotech.entity.Role;
import com.marcellusinfotech.entity.User;
import com.marcellusinfotech.entity.dto.LoginDto;
import com.marcellusinfotech.entity.dto.RegisterDto;
import com.marcellusinfotech.exception.APIException;
import com.marcellusinfotech.repository.RoleRepository;
import com.marcellusinfotech.repository.UserRepository;
import com.marcellusinfotech.security.JwtTokenProvider;
import com.marcellusinfotech.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {

		// add check for username exists in database
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new APIException("Username is already exists!.");
		}

		// add check for email exists in database
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new APIException("Email is already exists!.");
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);

		userRepository.save(user);

		return "User registered successfully!.";
	}
}
