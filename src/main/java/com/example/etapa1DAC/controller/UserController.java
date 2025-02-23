package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.controller.request.UserSignUpRequest;
import com.example.etapa1DAC.controller.response.UserResponse;
import com.example.etapa1DAC.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public UserResponse add(@Valid @RequestBody UserSignUpRequest request) {
        return userService.add(request);
    }

    @GetMapping("/me")
    public UserResponse userDetails() {
        return userService.find();
    }


}
