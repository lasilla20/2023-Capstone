package com.example.project;

import com.example.project.Crawling.ChromeDriver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EntityScan(basePackages = {"com.example.project.domain"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableJpaRepositories(basePackages = "com.example.project.Repository")
public class ProjectApplication {
/** DB 정해지면 application.properties 수정 후 위 annotation의 exclude 옵션 삭제 **/
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
