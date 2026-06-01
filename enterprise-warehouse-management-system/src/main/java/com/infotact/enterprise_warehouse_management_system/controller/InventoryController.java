package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // ✅ Receive stock
    @PostMapping("/receive/{productId}/{binId}/{qty}")
    public InventoryItem receive(@PathVariable Long productId,
                                 @PathVariable Long binId,
                                 @PathVariable int qty) {
        return service.receiveStock(productId, binId, qty);
    }

    // ✅ Fulfill order
    @PostMapping("/fulfill/{itemId}/{qty}")
    public ResponseEntity<String> fulfill(@PathVariable Long itemId,
                                          @PathVariable int qty) {
        service.fulfillOrder(itemId, qty);
        return ResponseEntity.ok("Order fulfilled successfully");
    }

    // ✅ Get all inventory items
    @GetMapping("/all")
    public List<InventoryItem> getAll() {
        return service.getAll();
    }
}
