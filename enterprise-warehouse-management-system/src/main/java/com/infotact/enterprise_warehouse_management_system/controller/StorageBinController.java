package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.model.StorageBin;
import com.infotact.enterprise_warehouse_management_system.service.StorageBinService;

@RestController
@RequestMapping("/storagebin")
public class StorageBinController {

	@Autowired
	private StorageBinService storageBinService;

	@PostMapping("/add")
	public StorageBin add(@RequestBody StorageBin bin) {
		return storageBinService.save(bin);
	}

	@GetMapping("/all")
	public List<StorageBin> getAll() {
		return storageBinService.getAll();
	}

	@GetMapping("/{id}")
	public StorageBin getById(@PathVariable Long id) {
		return storageBinService.getById(id);
	}
}