package com.example.library.user.controller;

import com.example.library.user.dto.UserDto;
import com.example.library.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "user")
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getId(@PathVariable String id) {
        return id;
    }

    @GetMapping("/get")
    public UserDto getUser(Long userNo) {
        return userService.getAllUser(userNo);
    }
}
