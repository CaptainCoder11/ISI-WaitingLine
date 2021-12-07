package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dto.UserDTO;
import com.isimtl.waitingline.entity.User;

import java.util.List;

public interface ICustomerService {

    public List<UserDTO> findAll();

    public UserDTO findById(int id);

    public void save(User user);
}
