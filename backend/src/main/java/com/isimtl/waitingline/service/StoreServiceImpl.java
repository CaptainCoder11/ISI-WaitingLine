package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dao.StoreDAO;
import com.isimtl.waitingline.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService{

    private StoreDAO storeDAO;

    @Autowired
    public StoreServiceImpl(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    @Override
    @Transactional
    public List<Store> findAll() {
        return storeDAO.findAll();
    }

    @Override
    @Transactional
    public Store findById(UUID id) {
        return storeDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Store store) {

    }
}
