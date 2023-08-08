package com.example.project.config.auth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
    private static final String REDIRECT_URI = "http://localhost:3000/logincheck";

    public MyAuthenticationSuccessHandler() {
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        log.info("SuccessHandler oAuth2User : {}", oAuth2User);
        response.sendRedirect(UriComponentsBuilder.fromUriString("http://localhost:3000/logincheck").queryParam("accessToken", new Object[]{"accessToken"}).queryParam("refreshToken", new Object[]{"refreshToken"}).queryParam("email", new Object[]{oAuth2User.getAttribute("email").toString()}).build().encode(StandardCharsets.UTF_8).toUriString());
    }
}