package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dto.StoreDTO;
import com.isimtl.waitingline.entity.Store;

import java.util.List;

public interface IStoreService {
    public List<StoreDTO> findAll();

    public Store findById(int id);

    public void save(Store store);
}
