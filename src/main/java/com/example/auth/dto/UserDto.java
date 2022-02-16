package com.example.auth.dto;

import com.example.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
