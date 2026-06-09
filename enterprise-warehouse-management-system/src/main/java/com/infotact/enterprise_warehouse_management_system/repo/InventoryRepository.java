package com.infotact.enterprise_warehouse_management_system.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.model.StorageBin;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
	 Optional<InventoryItem> findByProduct(Product product);
}
