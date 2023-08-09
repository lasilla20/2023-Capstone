package com.example.project.Controller;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class MyLogger {

    /** 리퀘스트 로깅 **/
    public void printRequestInfo(HttpServletRequest request, String path, String msg){
        System.out.println();
        System.out.println("--- NEW REQUEST ---");
        System.out.println("[" + request.getMethod() + "/" + request.getProtocol() + "] " + request.getRequestURL() + " / " + request.getQueryString());
        try{
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            System.out.println("[ " + messageBody + " ]");
        } catch (Exception e){}
        System.out.println(LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " --- " + path + " : " + msg);
        System.out.println();
    }
}
