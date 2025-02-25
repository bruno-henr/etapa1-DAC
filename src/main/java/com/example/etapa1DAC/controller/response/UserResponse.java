package com.example.etapa1DAC.controller.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Builder
public class UserResponse {

    private String nome;
    private String email;
	private List<String> permissoes;
}
