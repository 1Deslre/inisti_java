package com.example.springbootweb.controller;

import com.example.springbootdemostarter.module.DemoModule;
import com.example.springbootweb.entity.MyRequest;
import com.example.springbootweb.entity.Product;
import com.example.springbootweb.entity.User;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private DemoModule demoModule;

    @GetMapping("/demo")
    public String Demo() {
        return demoModule.exeModuleMethod();
    }

    @PostMapping("/processJson")
    public void processJson(@RequestBody MyRequest request) {

        User user = request.getUser();
        Product product = request.getProduct();
        Address address = user.getAddress();

    }
}
