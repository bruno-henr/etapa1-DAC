package com.example.etapa1DAC.controller.response;


import lombok.*;

import java.util.List;

@Getter @Builder @AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String name;
    private String email;
	private List<String> permissions;
}
