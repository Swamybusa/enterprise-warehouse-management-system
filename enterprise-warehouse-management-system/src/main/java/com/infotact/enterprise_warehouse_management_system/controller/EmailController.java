package com.infotact.enterprise_warehouse_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendMail() {

        emailService.sendEmail(
                "receiver@gmail.com",
                "Test Mail",
                "Hello from Spring Boot");

        return "Email Sent Successfully";
    }
}