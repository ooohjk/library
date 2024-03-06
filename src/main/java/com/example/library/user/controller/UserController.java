package com.example.library.user.controller;

import com.example.library.user.dto.UserDto;
import com.example.library.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
        userService.join(userDto.getUserId(), userDto.getUserPwd(), userDto.getUserName(), userDto.getTel(), userDto.getEmail(), userDto.getGender(), userDto.getUseFlg());
        return ResponseEntity.ok().body("Success Join!!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(String userId, String userPwd) {
        String token = userService.login(userId, userPwd);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/get/userNo")
    public UserDto getUserByUserNo(Long userNo) {
        return userService.getUserByUserNo(userNo);
    }

    @GetMapping("/get/userId")
    public UserDto getUserByUserId(String userId) {
        return userService.getUserByUserId(userId);
    }
}
