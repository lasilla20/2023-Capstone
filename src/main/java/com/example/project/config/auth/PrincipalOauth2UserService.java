package com.example.project.config.auth;


import com.example.project.Repository.UserRepository;
import com.example.project.domain.User;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class PrincipalOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);
        userProfile.setProvider(registrationId);
        this.saveOrUpdate(userProfile);
        Map<String, Object> customAttribute = this.customAttribute(attributes, userNameAttributeName, userProfile, registrationId);
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("USER")), customAttribute, userNameAttributeName);
    }

    private Map customAttribute(Map attributes, String userNameAttributeName, UserProfile userProfile, String registerId) {
        Map<String, Object> customAttribute = new LinkedHashMap();
        customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
        customAttribute.put("provider", registerId);
        customAttribute.put("name", userProfile.getName());
        customAttribute.put("email", userProfile.getEmail());
        return customAttribute;
    }

    private User saveOrUpdate(UserProfile userProfile) {
        User user = (User)this.userRepository.findByEmailAndProvider(userProfile.getEmail(), userProfile.getProvider()).map((m) -> {
            return m.update(userProfile.getName(), userProfile.getEmail());
        }).orElse(userProfile.toUser());
        return (User)this.userRepository.save(user);
    }

    public PrincipalOauth2UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
