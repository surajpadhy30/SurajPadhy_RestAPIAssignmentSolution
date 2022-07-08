package com.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class EmployeeManagementRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementRestApiApplication.class, args);
	}

}
