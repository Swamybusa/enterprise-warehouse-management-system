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
    @Autowired private InventoryRepository inventoryRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private StorageBinRepository binRepo;

    @Transactional
    public InventoryItem receiveStock(Long productId, Long binId, int qty) {
        Product product = productRepo.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        StorageBin bin = binRepo.findById(binId)
            .orElseThrow(() -> new RuntimeException("Bin not found"));

        Optional<InventoryItem> existingItem = inventoryRepo.findByProductAndStorageBin(product, bin);

        if (existingItem.isPresent()) {
            InventoryItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + qty);
            return inventoryRepo.save(item);
        } else {
            InventoryItem item = new InventoryItem();
            item.setProduct(product);
            item.setStorageBin(bin);
            item.setQuantity(qty);
            return inventoryRepo.save(item);
        }
    }

    @Transactional
    public void fulfillOrder(Long itemId, int qty) {
        InventoryItem item = inventoryRepo.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item not found"));
        if (item.getQuantity() < qty) {
            throw new RuntimeException("Insufficient stock");
        }
        item.setQuantity(item.getQuantity() - qty);
        inventoryRepo.save(item);
    }

    public List<InventoryItem> getAll() {
        return inventoryRepo.findAll();
    }
}

