package com.juliomesquita.security.service.security;

import com.juliomesquita.security.mapper.security.UserMapper;
import com.juliomesquita.security.model.security.dto.AuthenticateRequest;
import com.juliomesquita.security.model.security.dto.AuthenticationResponse;
import com.juliomesquita.security.model.security.dto.RegisterRequest;
import com.juliomesquita.security.persistence.security.entities.User;
import com.juliomesquita.security.persistence.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = this.userMapper.requestToUser(request);
        User userSave = this.userRepository.save(user);
        String jwtToken = this.jwtService.generateToken(userSave);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Optional<User> userOpt = this.userRepository.findByEmail(request.getUsername());
        String jwtToken = this.jwtService.generateToken(userOpt.get());

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();

    }
}
