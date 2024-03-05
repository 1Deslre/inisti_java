package com.example.springbootweb.controller;

import com.example.springbootweb.entity.MyRequest;
import com.example.springbootweb.entity.Product;
import com.example.springbootweb.entity.User;
import org.apache.tomcat.jni.Address;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @PostMapping("/processJson")
    public void processJson(@RequestBody MyRequest request) {
        User user = request.getUser();
        Product product = request.getProduct();
        Address address = user.getAddress();

        System.out.println("user = " + user);
        System.out.println("address = " + address);
        System.out.println("product = " + product);

    }
}
