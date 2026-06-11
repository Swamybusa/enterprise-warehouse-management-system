package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.repo.OrderRepository;
import com.infotact.enterprise_warehouse_management_system.repo.ProductRepository;
import com.infotact.enterprise_warehouse_management_system.repo.StorageBinRepository;
import com.infotact.enterprise_warehouse_management_system.repo.UserRepository;
import com.infotact.enterprise_warehouse_management_system.repo.WarehouseRepository;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private ProductRepository productRepopository;

    @Autowired
    private OrderRepository orderRepopository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private StorageBinRepository storageBinRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Map<String, Object> getDashboardData() {

        Map<String, Object> map = new HashMap<>();

        map.put("totalProducts", productRepopository.count());
        map.put("totalOrders", orderRepopository.count());
        map.put("totalWarehouses", warehouseRepository.count());
        map.put("totalStorageBins", storageBinRepository.count());
        map.put("totalUsers", userRepository.count());

        return map;
    }
}