package com.stockmarketapp.stocksmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableCaching
public class StocksManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksManagementServiceApplication.class, args);
	}

}
