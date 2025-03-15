package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.UserSignUpRequest;
import com.example.etapa1DAC.controller.response.UserResponse;
import com.example.etapa1DAC.domain.Permission;
import com.example.etapa1DAC.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserMapper {

    public static User toEntity(UserSignUpRequest request) {
        User entity = new User();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UserResponse toResponse(User entity) {
        return UserResponse.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .permissions(buildPermissoesResponse(entity.getPermissions()))
                .build();
    }

    private static List<String> buildPermissoesResponse(List<Permission> permissions) {
        return permissions.stream()
                .map(Permission::getName)
                .collect(toList());
    }




}
