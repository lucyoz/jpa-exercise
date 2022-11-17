package com.example.springbootjpa.model.dto;

import com.example.springbootjpa.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;

    public User toEntity(){
        return new User(this.id, this.username, this.password);
    }
}
