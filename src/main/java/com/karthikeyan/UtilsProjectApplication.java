package com.karthikeyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class UtilsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilsProjectApplication.class,
				args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
