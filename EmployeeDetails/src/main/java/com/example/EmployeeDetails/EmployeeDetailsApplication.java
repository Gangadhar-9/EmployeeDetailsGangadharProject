package com.example.EmployeeDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EmployeeDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailsApplication.class, args);
	}

}
