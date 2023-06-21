package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProjectApplication {
/** 깃허브 테스트용 주석 (편집 시 삭제 바람!!) **/
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
