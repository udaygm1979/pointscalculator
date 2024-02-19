package com.uday.pointscalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.uday.pointscalculator.dao")
@EnableTransactionManagement
public class PointsCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointsCalculatorApplication.class, args);
	}

}
