package com.infotact.enterprise_warehouse_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.dto.AuthRequest;
import com.infotact.enterprise_warehouse_management_system.model.User;
import com.infotact.enterprise_warehouse_management_system.repo.UserRepository;
import com.infotact.enterprise_warehouse_management_system.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request) {

		User user = userRepository.findByUsername(request.getUsername()).orElse(null);

		if (user != null && user.getPassword().equals(request.getPassword())) {

			return jwtUtil.generateToken(user.getUsername());
		}

		return "Invalid credentials";
	}
}