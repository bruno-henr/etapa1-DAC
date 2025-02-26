package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.controller.response.UserResponse;
import com.example.etapa1DAC.service.AuthenticatedUserService;
import com.example.etapa1DAC.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @PostMapping
    public UserResponse login() {
        return authenticatedUserService.getResponse();
    }


}
