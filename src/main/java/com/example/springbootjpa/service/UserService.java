package com.example.springbootjpa.service;

import com.example.springbootjpa.model.User;
import com.example.springbootjpa.model.dto.UserDto;
import com.example.springbootjpa.model.dto.UserRequest;
import com.example.springbootjpa.model.dto.UserResponse;
import com.example.springbootjpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUser(Long id){
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isPresent()){
            User user = optUser.get();
            return new UserResponse(user.getId(), user.getUsername(), "");
        }else{
            return new UserResponse(id,"","해당 id의 유저가 없습니다.");
        }

    }

    public UserResponse addUser(UserRequest userRequest){
        //dto를 entity로
        User user = userRequest.toEntity();

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());
    }
}
