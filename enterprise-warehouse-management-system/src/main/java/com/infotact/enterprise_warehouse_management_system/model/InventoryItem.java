package com.infotact.enterprise_warehouse_management_system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="inventory_items")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name="bin_id")
	@JsonIgnore
	private StorageBin storageBin;


	

}
