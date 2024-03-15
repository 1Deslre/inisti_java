package com.example.springbootweb.entity;

import lombok.Data;

@Data
public class Entity {
    private String field;

    // Constructor
    public Entity(String field) {
        this.field = field;
    }

    // Getter and Setter
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
