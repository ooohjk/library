package com.example.library.user.controller;

import com.example.library.user.dto.UserDto;
import com.example.library.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public UserDto getUser(String userId) {
        return userService.getAllUser(userId);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login() {
//        return ResponseEntity.ok().body(userService.login("", ""));
//    }
}
