package com.example.auth.rest;

import com.example.auth.dto.AdminUserDto;
import com.example.auth.dto.UserDto;
import com.example.auth.model.User;
import com.example.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "AdminRestControllerV1", description = "Для Админа")
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    @Operation(
            summary = "Получить пользователя по Id",
            description = "Админский доступ"
    )
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
