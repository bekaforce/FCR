package com.example.auth.rest;

import com.example.auth.dto.AuthenticationRequestDto;
import com.example.auth.model.User;
import com.example.auth.security.jwt.JwtTokenProvider;
import com.example.auth.service.UserService;
import com.example.auth.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Auth Controller", description = "Контроллер авторизации")
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, UserServiceImpl userServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping(value = "registration")
    @Operation(
            summary = "Регистрация",
            description = "Ввести имя и пароль для регистрации"
    )
    public ResponseEntity registration(@RequestBody User user) {
        return ResponseEntity.ok(userServiceImpl.register(user));
    }


    @PostMapping("login")
    @Operation(
            summary = "Залогиниться",
            description = "Ввести имя и пароль для логина"
    )
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null){
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
