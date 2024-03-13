package com.deslre.filedownload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;

@SpringBootApplication
public class FiledownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiledownloadApplication.class, args);
    }

    static {
        System.out.println("正在加载启动!!!@");
    }
}
