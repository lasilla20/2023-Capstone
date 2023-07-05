package com.example.project.config.auth;

import com.example.project.Repository.UserRepository;
import com.example.project.config.auth.userInfo.GoogleUserInfo;
import com.example.project.config.auth.userInfo.KakaoUserInfo;
import com.example.project.config.auth.userInfo.OAuth2UserInfo;
import com.example.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

//회원 정보를 db에 저장하는 과정
//db에 유저가 없다면 회원가입처리, 있다면 authentication(principalDetails)를 반환하여 SecurityContextHolder에 저장
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    //구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        System.out.println("getClientRegistration = " + userRequest.getClientRegistration());
        System.out.println("getAccessToken = " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("getAttributes = " + oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getClientId();

        if(provider.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        //현재 로그인 진행 중인 서비스 구분 : 구글
        String providerId = oAuth2User.getAttribute("sub");   //구글에서 해당 유저 고유 ID
        String username = provider + "_" + providerId;      //사용자가 입력한 적은 없지만 구별 위해 만들어줌
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";
        User userEntity = userRepository.findByName(username);
        if (userEntity == null){    //유저가 없다면
            userEntity = User.builder()  //회원가입 처리 - db 저장
                    .name(username)
                    .email(email)
                    .password(encoder.encode("password"))
                    .role(role)
                    .provider(provider)
                    .providerId(providerId).build();
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());

    }
}