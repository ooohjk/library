package com.example.library.user.controller;

import com.example.library.user.dto.UserDto;
import com.example.library.user.dto.UserLoginDto;
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
    public Object login(@RequestBody UserLoginDto userLoginDto) {
        String token = userService.login(userLoginDto.getUserId(), userLoginDto.getUserPwd());
        UserDto userDto = getUserByUserId(userLoginDto.getUserId());
        String result = "{\"userId\": \"" + userDto.getUserId() + "\", " +
                "\"userPwd\": \"" + userDto.getUserPwd() + "\"," +
                "\"userName\": \"" + userDto.getUserName() + "\"," +
                "\"tel\": \"" + userDto.getTel() + "\"," +
                "\"email\": \"" + userDto.getEmail() + "\"," +
                "\"gender\": \"" + userDto.getGender() + "\"," +
                "\"useFlg\": " + userDto.getUseFlg() + "," +
                "\"token\": \"" + token + "\"" +
                "}";
        JSONParser parser = new JSONParser();
        JSONObject obj;
        try {
            obj = (JSONObject)parser.parse(result);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return obj;
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
