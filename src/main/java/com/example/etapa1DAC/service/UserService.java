package com.example.etapa1DAC.service;

import com.example.etapa1DAC.controller.request.UserSignUpRequest;
import com.example.etapa1DAC.controller.response.UserResponse;
import com.example.etapa1DAC.domain.Function;
import com.example.etapa1DAC.domain.Permission;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.mapper.UserMapper;
import com.example.etapa1DAC.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public User getAuthenticatedUser() {
        return authenticatedUserService.get();
    }

    public UserResponse find() {
        User authenticatedUser = getAuthenticatedUser();
        return toResponse(authenticatedUser);
    }

    @Transactional
    public UserResponse add(UserSignUpRequest request) {

        User user = UserMapper.toEntity(request);
        user.setPassword(getPasswordEncoded(request.getPassword()));
        user.addPermission(getDefaultPermission());
        user.setActive(true);
        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    private String getPasswordEncoded(String password) {
        return passwordEncoder.encode(password);
    }

    private Permission getDefaultPermission(){
        Permission permission = new Permission();
        permission.setFunction(Function.USER);
        return permission;
    }


}
