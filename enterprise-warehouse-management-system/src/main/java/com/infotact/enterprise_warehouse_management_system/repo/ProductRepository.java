package com.infotact.enterprise_warehouse_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infotact.enterprise_warehouse_management_system.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
