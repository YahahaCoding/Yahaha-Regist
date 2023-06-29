package com.yahaha.cloud.signup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScans({@ComponentScan("com.yahaha.cloud")})
@EnableDiscoveryClient
@EnableScheduling
public class SignupApplication {
	public static void main(String[] args) {
		SpringApplication.run(SignupApplication.class, args);
	}
}
