package com.infotact.enterprise_warehouse_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotact.enterprise_warehouse_management_system.model.StorageBin;
import com.infotact.enterprise_warehouse_management_system.repo.StorageBinRepository;

@Service
public class StorageBinService {

    @Autowired
    private StorageBinRepository repo;

    public StorageBin save(StorageBin bin) {
        return repo.save(bin);
    }

    public List<StorageBin> getAll() {
        return repo.findAll();
    }

    public StorageBin getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("StorageBin not found"));
    }
}
