package com.example.etapa1DAC.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class UserSecurity implements UserDetails {


    private Long id;
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;

    private boolean accountNonExpired;
    private  boolean accountNonLocked;
    private  boolean credentialsNonExpired;
    private  boolean enabled;

    public UserSecurity(User user) {

        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();

        this.accountNonExpired = user.isActive();
        this.accountNonLocked = user.isActive();
        this.credentialsNonExpired = user.isActive();
        this.enabled = user.isActive();

        this.authorities = user.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getFunction().getRole()))
                .collect(Collectors.toList());
    }


}
