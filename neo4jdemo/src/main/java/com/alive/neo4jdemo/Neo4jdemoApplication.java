package com.alive.neo4jdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.alive.neo4jdemo.mapper")
@SpringBootApplication
public class Neo4jdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jdemoApplication.class, args);
    }

}
