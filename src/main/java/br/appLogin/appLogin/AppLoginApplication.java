package br.appLogin.appLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class AppLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppLoginApplication.class, args);
	}

}
