package com.isimtl.waitingline.controller;


import com.isimtl.waitingline.dto.StoreDTO;
import com.isimtl.waitingline.dto.UserDTO;
import com.isimtl.waitingline.entity.User;
import com.isimtl.waitingline.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(this.customerService.findAll());

    }

    @PostMapping("/login")
    public User loginCustomer(@RequestBody User user) {
        customerService.save(user);
        return user;
    }

    @PostMapping("/verify")
    public void verifyCustomer() {

    }
}
