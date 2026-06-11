package com.infotact.enterprise_warehouse_management_system.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.infotact.enterprise_warehouse_management_system.exception.InsufficientStockException;
import com.infotact.enterprise_warehouse_management_system.model.InventoryItem;
import com.infotact.enterprise_warehouse_management_system.model.Product;
import com.infotact.enterprise_warehouse_management_system.model.StorageBin;
import com.infotact.enterprise_warehouse_management_system.repo.InventoryRepository;
import com.infotact.enterprise_warehouse_management_system.repo.ProductRepository;
import com.infotact.enterprise_warehouse_management_system.repo.StorageBinRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StorageBinRepository storageBinRepository;

    @InjectMocks
    private InventoryService inventoryService;

    private Product product;
    private StorageBin bin;
    private InventoryItem item;

    @BeforeEach
    void setUp() {

        product = new Product();
        product.setId(1L);

        bin = new StorageBin();
        bin.setId(1L);

        item = new InventoryItem();
        item.setId(1L);
        item.setProduct(product);
        item.setStorageBin(bin);
        item.setQuantity(10);
    }

    @Test
    void shouldReceiveStock() {

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(storageBinRepository.findById(1L))
                .thenReturn(Optional.of(bin));

        when(inventoryRepository.findByProductAndStorageBin(product, bin))
                .thenReturn(Optional.empty());

        when(inventoryRepository.save(any(InventoryItem.class)))
                .thenReturn(item);

        InventoryItem result =
                inventoryService.receiveStock(1L, 1L, 10);

        assertNotNull(result);

        verify(inventoryRepository, times(1))
                .save(any(InventoryItem.class));
    }

    @Test
    void shouldFulfillOrder() {

        List<InventoryItem> items = Arrays.asList(item);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(inventoryRepository.findByProduct(product))
                .thenReturn(items);

        inventoryService.fulfillOrder(1L, 5);

        assertEquals(5, item.getQuantity());

        verify(inventoryRepository, times(1))
                .saveAll(items);
    }

    @Test
    void shouldThrowExceptionWhenStockInsufficient() {

        List<InventoryItem> items = Arrays.asList(item);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(inventoryRepository.findByProduct(product))
                .thenReturn(items);

        assertThrows(
                InsufficientStockException.class,
                () -> inventoryService.fulfillOrder(1L, 20)
        );
    }
}