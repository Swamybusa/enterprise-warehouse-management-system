package com.infotact.enterprise_warehouse_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotact.enterprise_warehouse_management_system.dto.InventoryRequest;
import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.model.StorageBin;
import com.infotact.enterprise_warehouse_management_system.repo.InventoryRepository;
import com.infotact.enterprise_warehouse_management_system.repo.ProductRepository;
import com.infotact.enterprise_warehouse_management_system.repo.StorageBinRepository;

@Service
public class InventoryService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StorageBinRepository storageBinRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	public InventoryItem addInventory(InventoryRequest req) {

		Product product = productRepository.findById(req.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found"));

		StorageBin bin = storageBinRepository.findById(req.getStorageBinId())
				.orElseThrow(() -> new RuntimeException("StorageBin not found"));

		InventoryItem item = new InventoryItem();
		item.setProduct(product);
		item.setStorageBin(bin);
		item.setQuantity(req.getQuantity());

		return inventoryRepository.save(item);
	}
}
