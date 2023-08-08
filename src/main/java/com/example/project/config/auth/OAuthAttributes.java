package com.example.project.config.auth;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    GOOGLE("google", (attributes) -> {
        UserProfile userProfile = new UserProfile();
       // userProfile.setName((String)attributes.get("name"));
        //userProfile.setEmail((String)attributes.get("email"));

        OAuth2User oauth2User = (OAuth2User) attributes;
        userProfile.setName((String) oauth2User.getAttributes().get("name"));
        userProfile.setName((String) oauth2User.getAttributes().get("email"));
        return userProfile;
    });

    private final String registrationId;
    private final Function<Map<String, Object>, UserProfile> of;

    private OAuthAttributes(String registrationId, Function of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static UserProfile extract(String registrationId, Map<String, Object> attributes) {
        return (UserProfile)((OAuthAttributes)Arrays.stream(values()).filter((provider) -> {
            return registrationId.equals(provider.registrationId);
        }).findFirst().orElseThrow(IllegalArgumentException::new)).of.apply(attributes);
    }
}
