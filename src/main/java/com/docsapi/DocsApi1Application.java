package com.docsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication  
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.docsapi"})
@EnableJpaRepositories(basePackages="com.docsapi")
@EnableTransactionManagement
@EntityScan(basePackages="com.docsapi")
public class DocsApi1Application {

	public static void main(String[] args) {
		SpringApplication.run(DocsApi1Application.class, args);
	}

}
