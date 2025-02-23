package com.example.etapa1DAC.service;

import com.example.etapa1DAC.UserSecurity;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class AuthenticatedUserService {

    @Autowired
    private UserRepository userRepository;

    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        return userSecurity.getId();
    }

    public User get() {
        return userRepository.findById(getId())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Usuário não existe ou não está autenticado"));
    }

}
