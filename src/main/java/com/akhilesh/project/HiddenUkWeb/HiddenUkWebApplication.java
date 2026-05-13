package com.akhilesh.project.HiddenUkWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HiddenUkWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiddenUkWebApplication.class, args);
	}

}
