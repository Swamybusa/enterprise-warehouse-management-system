package com.infotact.enterprise_warehouse_management_system.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infotact.enterprise_warehouse_management_system.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
