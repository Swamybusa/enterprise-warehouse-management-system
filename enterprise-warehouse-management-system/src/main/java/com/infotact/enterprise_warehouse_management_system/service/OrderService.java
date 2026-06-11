package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotact.enterprise_warehouse_management_system.enums.OrderStatus;
import com.infotact.enterprise_warehouse_management_system.exception.InsufficientStockException;
import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.model.Order;
import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.repo.InventoryRepository;
import com.infotact.enterprise_warehouse_management_system.repo.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    // CREATE ORDER
    public Order save(Order order) {

        Product product = order.getProduct();

        List<InventoryItem> items = inventoryRepository.findByProduct(product);

        int totalStock = items.stream()
                .mapToInt(InventoryItem::getQuantity)
                .sum();

        if (totalStock < order.getQuantity()) {
            throw new InsufficientStockException("Not enough stock available");
        }

        order.setStatus(OrderStatus.NEW);
        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    // PACK ORDER
    @Transactional
    public Order packOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new RuntimeException("Only NEW orders can be packed");
        }

        List<InventoryItem> items =
                inventoryRepository.findByProduct(order.getProduct());

        int remaining = order.getQuantity();

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

        order.setStatus(OrderStatus.PACKED);
        return orderRepository.save(order);
    }

    public Order shipOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PACKED) {
            throw new RuntimeException("Only PACKED orders can be shipped");
        }

        order.setStatus(OrderStatus.SHIPPED);
        return orderRepository.save(order);
    }

    public Order deliverOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new RuntimeException("Only SHIPPED orders can be delivered");
        }

        order.setStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }
}