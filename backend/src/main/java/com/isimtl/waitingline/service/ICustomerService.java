package com.isimtl.waitingline.service;

import com.isimtl.waitingline.entity.User;

import java.io.IOException;
import java.util.List;

public interface ICustomerService {

    public List<User> findAll();
    public User findById(int id);


    public User save(User user) throws IOException;
}
