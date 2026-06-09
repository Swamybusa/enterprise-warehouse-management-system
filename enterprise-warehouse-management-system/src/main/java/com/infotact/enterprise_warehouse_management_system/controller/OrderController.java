package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.model.Order;
import com.infotact.enterprise_warehouse_management_system.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }
    @PutMapping("/{id}/pack")
    public Order packOrder(@PathVariable Long id) {
        return orderService.packOrder(id);
    }
    @PutMapping("/{id}/ship")
	public Order shipOrder(@PathVariable Long id) {
	    return orderService.shipOrder(id);
	}

	@PutMapping("/{id}/deliver")
	public Order deliverOrder(@PathVariable Long id) {
	    return orderService.deliverOrder(id);
	}
}