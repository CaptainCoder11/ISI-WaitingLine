package com.isimtl.waitingline.controller;

import com.isimtl.waitingline.entity.Store;
import com.isimtl.waitingline.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreRestController {
    @GetMapping("/store")
    public String getAll(){
        return "Hello Store";
    }

}
