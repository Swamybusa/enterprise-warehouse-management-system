package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotact.enterprise_warehouse_management_system.model.StorageBin;
import com.infotact.enterprise_warehouse_management_system.model.Warehouse;
import com.infotact.enterprise_warehouse_management_system.repo.StorageBinRepository;
import com.infotact.enterprise_warehouse_management_system.repo.WarehouseRepository;

@Service
public class StorageBinService {
	@Autowired
	private StorageBinRepository storageBinRepository;
	@Autowired
	private WarehouseRepository warehouseRepository;

	public StorageBin save(Long warehouseId, StorageBin bin) {
		Warehouse warehouse = warehouseRepository.findById(warehouseId)
				.orElseThrow(() -> new RuntimeException("Warehouse not found"));
		bin.setWarehouse(warehouse);
		return storageBinRepository.save(bin);
	}

	public List<StorageBin> getAll() {
		return storageBinRepository.findAll();
	}

	public StorageBin getById(Long id) {
		return storageBinRepository.findById(id).orElseThrow(() -> new RuntimeException("Bin not found"));
	}
}
