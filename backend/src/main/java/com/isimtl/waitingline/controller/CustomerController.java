package com.isimtl.waitingline.controller;


import com.isimtl.waitingline.entity.AppointmentStatus;
import com.isimtl.waitingline.entity.Store;
import com.isimtl.waitingline.entity.User;
import com.isimtl.waitingline.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


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
            User User = customerService.save(user);
            return ResponseEntity.ok(User);
        } catch (IOException e) {
            new RuntimeException(e);
        }
        return null;
    }

    @PostMapping("/verify")
    public ResponseEntity<User> verifyCustomer(@RequestBody User user) {
        return ResponseEntity.ok(customerService.verify(user));
    }

    @GetMapping("/join-waiting-line/{userId}/{storeId}")
    public ResponseEntity<Boolean> joinWaitingList(@PathVariable("userId") int userId, @PathVariable("storeId") int storeId) {
        try {
            customerService.joinWaitingLine(userId, storeId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/remove-waiting-line/{userId}/{storeId}")
    public ResponseEntity<Boolean> removeWaitingList(@PathVariable("userId") int userId, @PathVariable("storeId") int storeId) {
        try {
            customerService.removeWaitingLine(userId, storeId, AppointmentStatus.In_Queue);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(true);
    }
}