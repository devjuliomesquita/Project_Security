package com.juliomesquita.security.mapper.security;

import com.juliomesquita.security.model.security.dto.RegisterRequest;
import com.juliomesquita.security.persistence.security.entities.User;
import com.juliomesquita.security.persistence.security.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestToUser(RegisterRequest registerRequest) {
        return User
                .builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(Role.USER)
                .build();
    }
}
