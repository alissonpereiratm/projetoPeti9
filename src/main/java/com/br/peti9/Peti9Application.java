package com.br.peti9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.br.peti9.entities",
		"com.br.peti9.repository",
		"com.br.peti9.controllers",
})

@EnableJpaRepositories
public class Peti9Application {

	public static void main(String[] args) {
		SpringApplication.run(Peti9Application.class, args);
	}

}
