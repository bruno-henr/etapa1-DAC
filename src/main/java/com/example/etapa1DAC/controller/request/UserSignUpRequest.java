package com.example.etapa1DAC.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter@Setter
public class UserSignUpRequest {

    @NotBlank
    private String name;

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    private List<String>permissions;

}
