package com.stockmarketapp.stocksmanagementservice;

import com.stockmarketapp.stocksmanagementservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableCaching
@EnableMongoRepositories
public class StocksManagementServiceApplication {

	@Autowired
	StockRepository stockRepository;

	public static void main(String[] args) {
		SpringApplication.run(StocksManagementServiceApplication.class, args);
	}

}
