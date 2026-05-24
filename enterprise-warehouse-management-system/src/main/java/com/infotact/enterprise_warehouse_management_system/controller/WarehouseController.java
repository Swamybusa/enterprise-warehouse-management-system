package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.model.Warehouse;
import com.infotact.enterprise_warehouse_management_system.service.WarehouseService;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;

	@PostMapping("/addWarehouse")
	Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
		return warehouseService.addWarehouse(warehouse);

	}

	@GetMapping("/getAllWarehouses")
	List<Warehouse> getAllWarehouses() {
		return warehouseService.getAllWarehouses();
	}

	@GetMapping("/getWarehouse/{id}")
	Warehouse getWarehouseById(@PathVariable("id") Long id) {
		return warehouseService.getWarehouseById(id);

	}

	@PutMapping("/updateWarehouse/{id}")
	Warehouse updateWarehouse(@PathVariable("id") Long id, @RequestBody Warehouse warehouse) {
		return warehouseService.updateWarehouse(id, warehouse);

	}

	@DeleteMapping("/deleteWarehouse/{id}")
	String deleteWarehouse(@PathVariable("id") Long id) {
		return warehouseService.deleteWarehouse(id);
	}

}
