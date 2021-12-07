package com.isimtl.waitingline.controller;

import com.isimtl.waitingline.dto.StoreDTO;
import com.isimtl.waitingline.entity.Store;
import com.isimtl.waitingline.service.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<StoreDTO>> getAll() {
        return ResponseEntity.ok(this.storeService.findAll());
    }

    @GetMapping("/{id}")
    public Store getById(@PathVariable("id") int id) {
        return storeService.findById(id);
    }
}
