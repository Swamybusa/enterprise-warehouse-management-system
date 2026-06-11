package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product save(Product p) {
		return productRepository.save(p);
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
