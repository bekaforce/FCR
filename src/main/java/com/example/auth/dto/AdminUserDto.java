package com.example.auth.dto;

import com.example.auth.model.Status;
import com.example.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String status;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setStatus(Status.valueOf(status));
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUserName(user.getUsername());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setStatus(user.getStatus().name());
        return adminUserDto;
    }
}
