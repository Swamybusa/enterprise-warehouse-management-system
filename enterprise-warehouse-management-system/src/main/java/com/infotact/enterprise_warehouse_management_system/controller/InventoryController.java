package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // Receive stock
    @PostMapping("/receive")
    public InventoryItem receive(@RequestParam Long productId,
                                 @RequestParam Long binId,
                                 @RequestParam int qty) {
        return service.receiveStock(productId, binId, qty);
    }

    // Fulfill order
    @PostMapping("/fulfill")
    public ResponseEntity<String> fulfill(@RequestParam Long productId,
                                          @RequestParam int qty) {
        service.fulfillOrder(productId, qty);
        return ResponseEntity.ok("Stock reduced successfully");
    }

    // Get all inventory
    @GetMapping
    public List<InventoryItem> getAll() {
        return service.getAll();
    }
}