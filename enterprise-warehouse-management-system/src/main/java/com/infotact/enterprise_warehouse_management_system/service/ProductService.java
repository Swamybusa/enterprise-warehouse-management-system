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

	public Product addProduct(Product product) {
		return productRepository.save(product);

	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Id is not found :"));

	}

	public Product updateProduct(Long productId, Product product) {

		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with id "+productId));

		existingProduct.setProductName(product.getProductName());
		existingProduct.setSku(product.getSku());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());

		return productRepository.save(existingProduct);
	}

	 public String deleteProduct(Long productId) {

	        Product existingProduct = productRepository.findById(productId)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found with Id : " + productId));

	        productRepository.delete(existingProduct);

	        return "Product deleted successfully with Id : " + productId;
	    }
}
