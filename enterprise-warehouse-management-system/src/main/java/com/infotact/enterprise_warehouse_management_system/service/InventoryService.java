package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.model.StorageBin;
import com.infotact.enterprise_warehouse_management_system.repo.InventoryRepository;
import com.infotact.enterprise_warehouse_management_system.repo.ProductRepository;
import com.infotact.enterprise_warehouse_management_system.repo.StorageBinRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StorageBinRepository storageBinRepository;

	// ✅ Receive stock into a bin
	@Transactional
	public InventoryItem receiveStock(Long productId, Long binId, int qty) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
		StorageBin bin = storageBinRepository.findById(binId)
				.orElseThrow(() -> new IllegalArgumentException("Bin not found with id: " + binId));

		Optional<InventoryItem> existingItem = inventoryRepository.findByProductAndStorageBin(product, bin);

		InventoryItem item;
		if (existingItem.isPresent()) {
			item = existingItem.get();
			item.setQuantity(item.getQuantity() + qty);
		} else {
			item = new InventoryItem();
			item.setProduct(product);
			item.setStorageBin(bin);
			item.setQuantity(qty);
		}
		product.setStockQuantity(product.getStockQuantity() + qty);
		productRepository.save(product);
		return inventoryRepository.save(item);
	}

	// ✅ Fulfill order by itemId
	@Transactional
	public void fulfillOrder(Long itemId, int qty) {
		InventoryItem item = inventoryRepository.findById(itemId)
				.orElseThrow(() -> new IllegalArgumentException("Inventory item not found with id: " + itemId));

		if (item.getQuantity() < qty) {
			throw new IllegalStateException(
					"Insufficient stock. Requested: " + qty + ", Available: " + item.getQuantity());
		}
		item.setQuantity(item.getQuantity() - qty);

		Product product = item.getProduct();
		product.setStockQuantity(product.getStockQuantity() - qty);

		productRepository.save(product);
		inventoryRepository.save(item);
	}

	// ✅ Get all inventory items
	public List<InventoryItem> getAll() {
		return inventoryRepository.findAll();
	}
}