package com.infotact.enterprise_warehouse_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.dto.AuthRequest;
import com.infotact.enterprise_warehouse_management_system.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        // TEMP login (we will connect DB later)
        if ("admin".equals(request.getUsername())
                && "admin".equals(request.getPassword())) {

            return jwtUtil.generateToken(request.getUsername());
        }

        return "Invalid credentials";
    }
}