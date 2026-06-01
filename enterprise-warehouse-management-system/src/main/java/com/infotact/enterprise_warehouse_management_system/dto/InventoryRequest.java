package com.infotact.enterprise_warehouse_management_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class InventoryRequest {

	@NotNull(message = "Product ID is required")
	private Long productId;
	@NotNull(message = "StorageBin ID is required")
	private Long storageBinId;
	@Positive(message = "Quantity must be greater than zero")
	private Integer quantity;

}
