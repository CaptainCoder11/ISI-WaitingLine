package com.isimtl.waitingline.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Value("${PROJECT_NAME}")
    private String name;

    @Value("${VERSION}")
    private String version;

    @GetMapping("/")
    public Map<String, String> getVersion(){
        HashMap<String, String> projectDetails = new HashMap<String,String>();
        projectDetails.put("name", name);
        projectDetails.put("version", version);
        return projectDetails;
    }
}
