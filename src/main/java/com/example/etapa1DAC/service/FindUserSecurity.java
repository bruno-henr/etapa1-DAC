package com.example.etapa1DAC.service;

import com.example.etapa1DAC.domain.UserSecurity;
import com.example.etapa1DAC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserSecurity implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> new UserSecurity(user))
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas"));

    }

}
