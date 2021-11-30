package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dao.StoreDAO;
import com.isimtl.waitingline.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoreServiceImpl implements StoreService{

    StoreDAO storeDAO;

    @Autowired
    public StoreServiceImpl(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    @Override
    public List<Store> findAll() {
        return storeDAO.findAll();
    }

    @Override
    public Store findById(int id) {
        return null;
    }

    @Override
    public void save(Store store) {

    }
}
