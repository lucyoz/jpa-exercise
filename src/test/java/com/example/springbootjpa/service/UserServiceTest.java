package com.example.springbootjpa.service;

import com.example.springbootjpa.model.User;
import com.example.springbootjpa.model.dto.UserRequest;
import com.example.springbootjpa.model.dto.UserResponse;
import com.example.springbootjpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Autowired
    private UserService userService;

//    @BeforeEach
//    void setUp(){
//        userService = new UserService(userRepository);      //수동 DI
//    }

    @Test
    @DisplayName("회원 등록 성공 메세지가 나오는지")
    void addTest() {

//        userService = new UserService(userRepository);
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l, "krk", "112233"));

        UserResponse userResponse = userService.addUser(new UserRequest("krk", "112233"));
        assertEquals("krk", userResponse.getUsername());
        assertEquals("회원 등록 성공", userResponse.getMessage());

    }
}