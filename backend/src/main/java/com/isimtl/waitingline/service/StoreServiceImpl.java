package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dto.StoreDTO;
import com.isimtl.waitingline.entity.Store;
import com.isimtl.waitingline.mapper.StoreMapper;
import com.isimtl.waitingline.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j

@Service
public class StoreServiceImpl implements IStoreService {

    private StoreRepository storeRepository;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;

    }

    @Override
    @Transactional
    public List<StoreDTO> findAll() {
        List<Store> stores = this.storeRepository.findAll();
        System.out.println(stores);
        System.out.println(storeMapper.toDto(stores));
        return storeMapper.toDto(stores);
    }

    @Override
    @Transactional
    public Store findById(int id) {
        Optional<Store> result = storeRepository.findById(Integer.valueOf(id));
        Store store = null;
        if (result.isPresent())
            store = result.get();
        else
            throw new RuntimeException("Employee not found with id - " + id);
        return store;
    }

    @Override
    @Transactional
    public void save(Store store) {

    }
}
