package com.example.etapa1DAC.service;

import com.example.etapa1DAC.controller.request.UserSignUpRequest;
import com.example.etapa1DAC.controller.response.UserResponse;
import com.example.etapa1DAC.domain.Permission;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.mapper.UserMapper;
import com.example.etapa1DAC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.etapa1DAC.mapper.UserMapper.toResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    public UserResponse find() {
        User authenticatedUser = authenticatedUserService.get();
        return toResponse(authenticatedUser);
    }

    public UserResponse add(UserSignUpRequest request) {

        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        request.getPermissions()
                .forEach(p -> {
                    Permission permission = new Permission();
                    permission.setName(p);


                    userRepository.save(user);
                });

        return UserMapper.toResponse(user);
    }

}
