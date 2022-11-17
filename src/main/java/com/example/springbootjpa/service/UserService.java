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
            return new UserResponse(user.getId(), user.getUsername(), "회원등록성공");
        }else{
            return new UserResponse(id,"","해당 id의 유저가 없습니다.");
        }

    }

    public UserResponse addUser(UserRequest userRequest){
        //dto를 entity로
        User user = userRequest.toEntity();

        //저장하기 전 username으로 select해봅니다.
        //있으면 중복되었습니다 메세지로 알려주고 .save하지 않음
        /*
        Optional<User> selectedUser = userRepository.findByUsername(userRequest.getUsername());
        if(selectedUser.isPresent()){
            return new UserResponse(null, userRequest.getUsername(), "이 user는 이미 존재합니다. 다른 이름을 사용합니다.");
        }else{
            User savedUser = userRepository.save(user);
            return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());
        }*/

        User selectedUser = userRepository.findByUsername(userRequest.getUsername());
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());

    }
}
