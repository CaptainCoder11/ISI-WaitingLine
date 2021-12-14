package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.FBUser;
import com.isimtl.waitingline.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICustomerService {

    public List<User> findAll();
    public User findById(int id);
    public User save(User user) throws IOException;
    public User verify(User user);
    public FBUser getFbUser(int userId, int storeId);
    public void joinWaitingLine(int userId, int storeId) throws ExecutionException, InterruptedException;
    public void removeWaitingLine(int userId, int storeId) throws ExecutionException, InterruptedException;
}
