package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotact.enterprise_warehouse_management_system.model.Warehouse;
import com.infotact.enterprise_warehouse_management_system.repo.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;

	public Warehouse addWarehouse(Warehouse warehouse) {
		return warehouseRepository.save(warehouse);

	}

	public List<Warehouse> getAllWarehouses() {
		return warehouseRepository.findAll();

	}

	public Warehouse getWarehouseById(Long id) {
		return warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("warehouse id is not found : "));
	}

	public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
		Warehouse exstd = warehouseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("warehouse id is not found : "));
		exstd.setWarehouseName(warehouse.getWarehouseName());
		exstd.setLocation(warehouse.getLocation());

		return warehouseRepository.save(exstd);

	}

	public String deleteWarehouse(Long id) {
		warehouseRepository.deleteById(id);
		return "warehouse deleted with id : " + id;

	}

}
