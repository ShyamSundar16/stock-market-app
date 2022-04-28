package com.stockmarketapp.searchstocksservice.util;

import com.stockmarketapp.searchstocksservice.config.KafkaConsumerConfiguration;
import com.stockmarketapp.searchstocksservice.model.Company;
import com.stockmarketapp.searchstocksservice.model.Stock;
import com.stockmarketapp.searchstocksservice.repository.CompanyRepository;
import com.stockmarketapp.searchstocksservice.repository.StockRepository;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Collections;

public class ConsumerInitiate {

    @Autowired
    CompanyRepository companyRepository;
    StockRepository stockRepository;

    private static final String ADD_COMPANY_TOPIC = "add_company";
    private static final String ADD_STOCK_TOPIC = "add_stock";
    private static final String DELETE_COMPANY_TOPIC = "delete_company";

    public void startConsumer() {
        KafkaConsumer<String, Company> addCompanyConsumer = KafkaConsumerConfiguration.addCompanyConsumer();
        KafkaConsumer<String, Stock> addStockConsumer = KafkaConsumerConfiguration.addStockConsumer();
        KafkaConsumer<String, String> deleteCompanyConsumer = KafkaConsumerConfiguration.deleteCompanyConsumer();

        addCompanyConsumer.subscribe(Collections.singletonList(ADD_COMPANY_TOPIC));
        addStockConsumer.subscribe(Collections.singletonList(ADD_STOCK_TOPIC));
        deleteCompanyConsumer.subscribe(Collections.singletonList(DELETE_COMPANY_TOPIC));

        while (true) {
            try {
                ConsumerRecords<String, Company> companyConsumerRecords = addCompanyConsumer.poll(Duration.ofSeconds(1));
                ConsumerRecords<String, Stock> stockConsumerRecords = addStockConsumer.poll(Duration.ofSeconds(1));
                ConsumerRecords<String, String> deleteCompanyRecords = deleteCompanyConsumer.poll(Duration.ofSeconds(1));

                companyConsumerRecords.forEach(stringCompanyConsumerRecord -> {
                    companyRepository.save(stringCompanyConsumerRecord.value());
                });

                deleteCompanyRecords.forEach(deleteRecord -> {
                    companyRepository.deleteById(deleteRecord.value());
                });

                stockConsumerRecords.forEach(stringStockConsumerRecord -> {
                    stockRepository.save(stringStockConsumerRecord.value());
                });

            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
    }
}
