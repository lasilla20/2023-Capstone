package com.example.project.Controller;

import com.example.project.Repository.UserRepository;
import com.example.project.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class HelloWorldController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }

    @GetMapping("/loginForm")
    public String login(){
        return "login";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails = " + principalDetails.getUser());
        return "user";
    }

}
