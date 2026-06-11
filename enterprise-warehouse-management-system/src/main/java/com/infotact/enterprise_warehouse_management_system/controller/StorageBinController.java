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

import com.infotact.enterprise_warehouse_management_system.model.StorageBin;
import com.infotact.enterprise_warehouse_management_system.service.StorageBinService;

@RestController
@RequestMapping("/api/storagebins")
public class StorageBinController {
	@Autowired
	private StorageBinService storageBinservice;

	@PostMapping("/add/{warehouseId}")
	public StorageBin add(@PathVariable Long warehouseId, @RequestBody StorageBin bin) {
		return storageBinservice.save(warehouseId, bin);
	}

	@GetMapping("/all")
	public List<StorageBin> getAll() {
		return storageBinservice.getAll();
	}

	@GetMapping("/{id}")
	public StorageBin getById(@PathVariable Long id) {
		return storageBinservice.getById(id);
	}
	// UPDATE BIN
	@PutMapping("/{id}")
	public StorageBin update(@PathVariable Long id,
	                         @RequestBody StorageBin bin) {

	    StorageBin existing = storageBinservice.getById(id);

	    existing.setBinCode(bin.getBinCode());
	    existing.setCapacity(bin.getCapacity());

	    return storageBinservice.save(
	            existing.getWarehouse().getId(),
	            existing);
	}


	// DELETE BIN
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

	    storageBinservice.delete(id);
	}
}
