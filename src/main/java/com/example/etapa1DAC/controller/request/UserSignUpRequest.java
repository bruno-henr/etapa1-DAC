package com.example.etapa1DAC.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter@Setter
public class
UserSignUpRequest {

    @NotBlank(message = "o nome é obrigatório")
    private String name;

    @NotBlank(message = "o email é obrigatório")
    @Email(message = "formato de email invalido")
    private String email;

    @NotBlank(message = "obrigatório")
    private String password;


}
