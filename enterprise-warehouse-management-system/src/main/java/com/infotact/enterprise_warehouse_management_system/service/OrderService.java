package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.infotact.enterprise_warehouse_management_system.enums.OrderStatus;
import com.infotact.enterprise_warehouse_management_system.model.Order;
import com.infotact.enterprise_warehouse_management_system.repo.OrderRepository;

public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order save(Order order) {
		order.setStatus(OrderStatus.NEW);
		return orderRepository.save(order);
	}

	public List<Order> getAll() {
		return orderRepository.findAll();
	}
}
