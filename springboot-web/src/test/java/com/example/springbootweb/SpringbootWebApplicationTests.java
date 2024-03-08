package com.example.springbootweb;
import com.deslre.demospringbootstarter.entity.User;
import com.deslre.demospringbootstarter.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootWebApplicationTests {

    @Resource
    private IUserService userService;

    @Test
    void name() {
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }
}
