package com.infotact.enterprise_warehouse_management_system.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="storagebin")
public class StorageBin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String binCode;
	private Integer capacity;
	
	@ManyToOne
	@JoinColumn(name="warehouse_id")
	@JsonBackReference
	private Warehouse warehouse;
	
	@OneToMany(mappedBy = "storageBin",cascade=CascadeType.ALL)
	private List<InventoryItem> items=new ArrayList<>();

}
