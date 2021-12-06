package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dto.StoreDTO;
import com.isimtl.waitingline.entity.Store;

import java.util.List;
import java.util.UUID;

public interface IStoreService {
    public List<StoreDTO> findAll();

    public Store findById(UUID id);

    public void save(Store store);
}
