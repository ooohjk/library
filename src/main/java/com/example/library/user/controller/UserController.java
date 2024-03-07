package com.example.library.user.controller;

import com.example.library.user.dto.UserDto;
import com.example.library.user.dto.UserLoginDto;
import com.example.library.user.dto.UserLoginResDto;
import com.example.library.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

    @ResponseBody
    @PostMapping("/login")
    public UserLoginResDto login(@RequestBody UserLoginDto userLoginDto) {
        UserLoginResDto userLoginResDto = userService.login(userLoginDto.getUserId(), userLoginDto.getUserPwd());
        return userLoginResDto;
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
