package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.Store;

import java.util.List;

public interface StoreService {
    public List<Store> findAll();

    public Store findById(int id);

    public void save(Store store);
}
