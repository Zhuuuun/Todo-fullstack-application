package com.zhunism.backendapp.controller;

import com.zhunism.backendapp.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.dto.UserResponseDTO;
import com.zhunism.backendapp.entity.User;
import com.zhunism.backendapp.exception.DuplicatedUserException;
import com.zhunism.backendapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SignupUserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid SignupRequestDTO signupRequestDTO)  {
        UserResponseDTO createdUser = authService.createUser(signupRequestDTO);

        if(createdUser == null) return new ResponseEntity<>("User isn't created, Try again later.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return authService.findAll();
    }
}
