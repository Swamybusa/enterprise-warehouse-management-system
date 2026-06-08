package com.infotact.enterprise_warehouse_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.service.BarcodeService;
import com.infotact.enterprise_warehouse_management_system.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private BarcodeService barcodeService;

    // GET QR CODE
    @GetMapping("/{id}/qrcode")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Long id) throws Exception {

        Product product = service.getById(id);

        byte[] qrCode = barcodeService.generateQRCode(product.getSku());

        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(qrCode);
    }

    // GET ALL PRODUCTS
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    // CREATE PRODUCT
    @PostMapping
    public Product add(@RequestBody Product p) {
        return service.save(p);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }
}