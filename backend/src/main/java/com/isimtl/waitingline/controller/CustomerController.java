package com.isimtl.waitingline.controller;


import com.isimtl.waitingline.entity.User;
import com.isimtl.waitingline.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(this.customerService.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginCustomer(@RequestBody User user) {
        try {
            User User =  customerService.save(user);
            return ResponseEntity.ok(User);
        } catch (IOException e) {
            new RuntimeException("Internal server error Please try after some time.");
        }
        return null;
    }

}
