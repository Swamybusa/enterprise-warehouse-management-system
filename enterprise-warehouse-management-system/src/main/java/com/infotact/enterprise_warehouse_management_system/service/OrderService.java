package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotact.enterprise_warehouse_management_system.enums.OrderStatus;
import com.infotact.enterprise_warehouse_management_system.exception.InsufficientStockException;
import com.infotact.enterprise_warehouse_management_system.model.Order;
import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.repo.OrderRepository;
import com.infotact.enterprise_warehouse_management_system.repo.ProductRepository;
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;
	
	public Order save(Order order) {
		order.setStatus(OrderStatus.NEW);
		return orderRepository.save(order);
	}

	public List<Order> getAll() {
		return orderRepository.findAll();
	}
	@Transactional
	public Order packOrder(Long orderId) {

	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	    Product product = order.getProduct();

	    if (product.getStockQuantity() < order.getQuantity()) {
	        throw new InsufficientStockException("Insufficient stock available");
	    }

	    product.setStockQuantity(
	            product.getStockQuantity() - order.getQuantity());

	    order.setStatus(OrderStatus.PACKED);

	    productRepository.save(product);

	    return orderRepository.save(order);
	}
}
