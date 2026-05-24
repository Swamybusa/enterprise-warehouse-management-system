package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);

	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/getProduct/{id}")
	public Product getProductById(@PathVariable("id") Long productId) {
		return productService.getProductById(productId);

	}

	@PutMapping("/updateProduct/{id}")
	public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
		return productService.updateProduct(productId, product);

	}

	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") Long productId) {

		return productService.deleteProduct(productId);
	}

}
