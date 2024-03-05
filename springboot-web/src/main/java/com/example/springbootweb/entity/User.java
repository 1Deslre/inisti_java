package com.example.springbootweb.entity;

import lombok.Data;
import org.apache.tomcat.jni.Address;

@Data
public class User {
    private int id;
    private String name;
    private Address address;
}

