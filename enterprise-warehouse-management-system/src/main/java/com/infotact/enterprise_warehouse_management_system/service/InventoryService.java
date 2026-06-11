package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotact.enterprise_warehouse_management_system.exception.InsufficientStockException;
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

    // =========================
    // RECEIVE STOCK (ADD/UPDATE)
    // =========================
    @Transactional
    public InventoryItem receiveStock(Long productId, Long binId, int qty) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        StorageBin bin = storageBinRepository.findById(binId)
                .orElseThrow(() -> new IllegalArgumentException("Storage Bin not found"));

        Optional<InventoryItem> existingItem =
                inventoryRepository.findByProductAndStorageBin(product, bin);

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

        return inventoryRepository.save(item);
    }

    // =========================
    // FULFILL ORDER (DEDUCT STOCK)
    // =========================
    @Transactional
    public void fulfillOrder(Long productId, int qty) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        List<InventoryItem> items =
                inventoryRepository.findByProduct(product);

        int totalStock = items.stream()
                .mapToInt(InventoryItem::getQuantity)
                .sum();

        if (totalStock < qty) {
            throw new InsufficientStockException("Insufficient stock available");
        }

        int remaining = qty;

        for (InventoryItem item : items) {

            if (remaining == 0) break;

            int available = item.getQuantity();

            if (available >= remaining) {
                item.setQuantity(available - remaining);
                remaining = 0;
            } else {
                item.setQuantity(0);
                remaining -= available;
            }
        }

        inventoryRepository.saveAll(items);
    }

    // =========================
    // GET ALL INVENTORY
    // =========================
    public List<InventoryItem> getAll() {
        return inventoryRepository.findAll();
    }
}