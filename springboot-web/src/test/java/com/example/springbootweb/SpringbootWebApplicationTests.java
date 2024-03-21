package com.example.springbootweb;

import com.deslre.demospringbootstarter.entity.User;
import com.deslre.demospringbootstarter.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

class SpringbootWebApplicationTests {
    @Test
    void name() {
        int a = 1;
        int b = (++a) + (++a) + (++a);
        System.out.println("b = " + b);

    }
}
