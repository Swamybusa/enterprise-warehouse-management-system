package com.infotact.enterprise_warehouse_management_system.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="warehouses")
@Data
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String warehouseName;
	private String location;
	
	@OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<StorageBin> bins = new ArrayList<>();
}

