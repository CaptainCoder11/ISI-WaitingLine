package com.isimtl.waitingline.service;

import com.isimtl.waitingline.dto.UserDTO;
import com.isimtl.waitingline.entity.User;
import com.isimtl.waitingline.mapper.UserMapper;
import com.isimtl.waitingline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{

    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    public CustomerServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> user = this.userRepository.findAll();
        System.out.println(user);
        System.out.println(userMapper.toDto(user));
        return userMapper.toDto(user);    }

    @Override
    public UserDTO findById(int id) {
        return null;
    }

    @Override
    public void save(User user) {
        user.setDateTime(LocalDateTime.now());
        userRepository.save(user);
    }
}
