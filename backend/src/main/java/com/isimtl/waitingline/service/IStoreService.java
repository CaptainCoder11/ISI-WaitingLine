package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.Store;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IStoreService {
    public List<Store> findAll();

    public Store findById(int id);

    public void save(Store store);

    public void arrival(int userId, int storeId) throws ExecutionException, InterruptedException;
    public void departure(int userId, int storeId) throws ExecutionException, InterruptedException;
    public void remove(int userId, int storeId) throws ExecutionException, InterruptedException;
}
