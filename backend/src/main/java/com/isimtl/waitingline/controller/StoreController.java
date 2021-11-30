package com.isimtl.waitingline.controller;

import com.isimtl.waitingline.entity.Store;
import com.isimtl.waitingline.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/store")
    public List<Store> getAll() {
        return storeService.findAll();
    }

    @GetMapping("/store/{id}")
    public Store getById(@PathVariable("id") UUID id){
        return storeService.findById(id);
    }


}
