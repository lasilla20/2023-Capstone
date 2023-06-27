package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProjectApplication {
/** 깃허브 테스트용 주석 (편집 시 삭제 바람!!) **/
/** DB 정해지면 application.properties 수정 후 위 annotation의 exclude 옵션 삭제 **/


	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}

	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
