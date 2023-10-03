package com.juliomesquita.security.mapper.security;

import com.juliomesquita.security.model.security.dto.RegisterRequest;
import com.juliomesquita.security.persistence.security.entities.User;
import com.juliomesquita.security.persistence.security.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    public User requestToUser(RegisterRequest registerRequest) {
        return User
                .builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
    }
}
