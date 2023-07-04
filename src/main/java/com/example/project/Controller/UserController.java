package com.example.project.Controller;

import com.example.project.config.auth.PrincipalDetails;
import com.example.project.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping(value = "/main")
public class UserController {

    /**스프링 시큐리티: user 정보를 받아오고 싶으면 PrincialDetails에서 user를 꺼내다 쓴다. (OAuth 로그인으로 인하여)**/
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return login();
    }

    @GetMapping("/login/google")
    public String loginGoogle() {
        return login();
    }

    /* 통합 로그아웃
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, PrincipalDetails principalDetails) throws Exception {
        logger.info("로그아웃 진행중···");
        User user = principalDetails.getUser();
        switch (user.getProvider()){
            case "google":
                session.invalidate();
                break;
            case "kakao":
                String accessToken = (String) session.getAttribute("access_Token");
                session.removeAttribute("access_Token");
                session.removeAttribute("userId");
                break;
        }
        return "main";
    }
    */
}
