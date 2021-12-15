package com.isimtl.waitingline.controller;

import com.isimtl.waitingline.entity.Store;
import com.isimtl.waitingline.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final IStoreService storeService;

    @Autowired
    public StoreController(IStoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Store>> getAll() {
        return ResponseEntity.ok(this.storeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(storeService.findById(id));
    }

    @GetMapping(value = "/{storeId}/arrival/{userId}")
    public ResponseEntity<Boolean> userArrival(@PathVariable("userId") int userId, @PathVariable("storeId") int storeId) {
        try {
            storeService.arrival(userId, storeId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/{storeId}/departure/{userId}")
    public ResponseEntity<Boolean> userDeparture(@PathVariable("userId") int userId, @PathVariable("storeId") int storeId) {
        try {
            storeService.departure(userId, storeId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/{storeId}/remove/{userId}")
    public ResponseEntity<Boolean> userRemove(@PathVariable("userId") int userId, @PathVariable("storeId") int storeId) {
        try {
            storeService.remove(userId, storeId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(true);
    }
}
