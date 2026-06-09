package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotact.enterprise_warehouse_management_system.enums.OrderStatus;
import com.infotact.enterprise_warehouse_management_system.exception.InsufficientStockException;
import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.model.Order;
import com.infotact.enterprise_warehouse_management_system.repo.InventoryRepository;
import com.infotact.enterprise_warehouse_management_system.repo.OrderRepository;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Order save(Order order) {
        order.setStatus(OrderStatus.NEW);
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order packOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 👉 GET INVENTORY (stock source)
        InventoryItem item = inventoryRepository
                .findByProduct(order.getProduct())
                .orElseThrow(() -> new InsufficientStockException("Stock not found"));

        if (item.getQuantity() < order.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock available");
        }

        // 👉 REDUCE STOCK FROM INVENTORY
        item.setQuantity(item.getQuantity() - order.getQuantity());

        inventoryRepository.save(item);

        order.setStatus(OrderStatus.PACKED);

        return orderRepository.save(order);
    }

    public Order shipOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.SHIPPED);

        return orderRepository.save(order);
    }

    public Order deliverOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.DELIVERED);

        return orderRepository.save(order);
    }
}