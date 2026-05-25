package com.infotact.enterprise_warehouse_management_system.dto;

import lombok.Data;

@Data
public class InventoryRequest {

	private Long productId;
	private Long storageBinId;
	private Integer quantity;

}
