package com.isimtl.waitingline.controller;


import com.isimtl.waitingline.entity.FBUser;
import com.isimtl.waitingline.service.FBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/test")
public class TestController {

    FBUserService fbUserService;

    @Autowired
    public TestController(FBUserService fbUserService) {
        this.fbUserService = fbUserService;
    }

    @GetMapping("/version")
    public String version(){
        return "V 1.0";
    }

    @PostMapping("/firebase/add")
    public String createUser(@RequestBody FBUser user ) throws InterruptedException, ExecutionException {
        return fbUserService.save(user);
    }

}
