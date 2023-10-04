package com.juliomesquita.security.service.security;

import com.juliomesquita.security.persistence.security.entities.CustomOAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService extends DefaultOAuth2UserService {

    private final CustomOAuthUser customOAuthUser;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User userOAuth = super.loadUser(userRequest);
        return new CustomOAuthUser(userOAuth);


    }
}
