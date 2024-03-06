package com.example.library.user.controller;

import com.example.library.user.dto.UserDto;
import com.example.library.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
        userService.join(userDto.getUserId(), userDto.getUserPwd(), userDto.getUserName(), userDto.getTel(), userDto.getEmail(), userDto.getGender(), userDto.getUseFlg());
        return ResponseEntity.ok().body("Success Join!!");
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
//        String token = userService.login(userDto.getUserId(), userDto.getUserPwd());
//        return ResponseEntity.ok().body(token);
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(String userId, String userPwd) {
        String token = userService.login(userId, userPwd);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/get")
    public UserDto getUser(Long userNo) {
        return userService.getUser(userNo);
    }
}
