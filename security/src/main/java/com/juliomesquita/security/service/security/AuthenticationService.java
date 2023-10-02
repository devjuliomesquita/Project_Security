package com.juliomesquita.security.service.security;

import com.juliomesquita.security.model.security.dto.AuthenticateRequest;
import com.juliomesquita.security.model.security.dto.AuthenticationResponse;
import com.juliomesquita.security.model.security.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request) {
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        return null;
    }
}
