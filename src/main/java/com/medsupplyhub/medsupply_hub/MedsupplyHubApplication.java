package com.medsupplyhub.medsupply_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MedsupplyHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedsupplyHubApplication.class, args);
	}

}
