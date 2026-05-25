package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	@Autowired
	private InventoryService service;

	@PostMapping("/receive/{productId}/{binId}/{qty}")
	public InventoryItem receive(@PathVariable Long productId, @PathVariable Long binId, @PathVariable int qty) {
		return service.receiveStock(productId, binId, qty);
	}

	@PostMapping("/fulfill/{itemId}/{qty}")
	public ResponseEntity<String> fulfill(@PathVariable Long itemId, @PathVariable int qty) {
		service.fulfillOrder(itemId, qty);
		return ResponseEntity.ok("Order fulfilled successfully");
	}

	@GetMapping("/all")
	public List<InventoryItem> getAll() {
		return service.getAll();
	}
}
