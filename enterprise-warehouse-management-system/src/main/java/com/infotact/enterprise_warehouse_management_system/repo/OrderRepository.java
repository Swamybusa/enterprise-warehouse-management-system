package com.infotact.enterprise_warehouse_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infotact.enterprise_warehouse_management_system.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
