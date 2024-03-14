package com.example.library.domain.user.controller;

import com.example.library.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerSpringBootTest {

    @Autowired
    UserService userService;

    @Test
    void 유저엔티티_찜조회(){
        userService.getMyHeartList(1L);
    }
}
