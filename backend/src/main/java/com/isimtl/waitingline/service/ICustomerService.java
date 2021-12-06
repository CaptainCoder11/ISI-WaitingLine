package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dto.UserDTO;
import com.isimtl.waitingline.entity.User;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    public List<UserDTO> findAll();

    public UserDTO findById(UUID id);

    public void save(User user);
}
