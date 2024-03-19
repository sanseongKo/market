package com.market.marketJpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MarketJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketJpaApplication.class, args);
	}

}
