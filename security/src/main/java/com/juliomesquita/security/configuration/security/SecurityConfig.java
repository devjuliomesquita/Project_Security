package com.juliomesquita.security.configuration.security;

import com.juliomesquita.security.persistence.security.entities.CustomOAuthUser;
import com.juliomesquita.security.service.security.AuthenticationService;
import com.juliomesquita.security.service.security.OAuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final OAuthService oAuthService;
    private final AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/api/v1/auth/**","/login", "/oauth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .oauth2Login((oauth2Login) ->
                        oauth2Login
                                .loginPage("")
                                .userInfoEndpoint((userInfoEndpoint) ->
                                        userInfoEndpoint
                                                .userService(oAuthService))
                                .successHandler(new AuthenticationSuccessHandler() {
                                    @Override
                                    public void onAuthenticationSuccess(
                                            HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication
                                    ) throws IOException, ServletException {
                                        authenticationService.oAuthUser((CustomOAuthUser) authentication.getPrincipal());

                                    }
                                })
                )

                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
