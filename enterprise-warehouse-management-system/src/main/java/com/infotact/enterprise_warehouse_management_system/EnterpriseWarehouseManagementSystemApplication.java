package com.infotact.enterprise_warehouse_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
@SpringBootApplication
public class EnterpriseWarehouseManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseWarehouseManagementSystemApplication.class, args);
    }
}