package com.example.springbootjpa.controller;

import com.example.springbootjpa.model.dto.UserDto;
import com.example.springbootjpa.model.dto.UserRequest;
import com.example.springbootjpa.model.dto.UserResponse;
import com.example.springbootjpa.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse userDto = userService.getUser(id);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
        UserResponse response = userService.addUser(userRequest);
        return ResponseEntity.ok().body(response);
    }
}
