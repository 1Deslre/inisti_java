package com.alive.neo4jdemo.neotest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class TwoTest {

    @Test
    void name() {
        tes("张三",1,2,3,4);
    }

    public void tes(String name, Object ...args){
        for (Object arg : args) {
            System.out.println("arg = " + arg);
        }
    }

}
