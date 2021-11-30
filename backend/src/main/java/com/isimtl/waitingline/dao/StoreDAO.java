package com.isimtl.waitingline.dao;

import com.isimtl.waitingline.entity.Store;

import java.util.List;

public interface StoreDAO {

    public List<Store> findAll();

    public Store findById(int id);

    public void save(Store store);

}
