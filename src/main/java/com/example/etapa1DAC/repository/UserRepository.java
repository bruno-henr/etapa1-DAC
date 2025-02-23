package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
