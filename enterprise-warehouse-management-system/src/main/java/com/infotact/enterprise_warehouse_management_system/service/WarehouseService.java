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

    public Warehouse save(Warehouse w) {
        return warehouseRepository.save(w);
    }

    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    public Warehouse getById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }
}