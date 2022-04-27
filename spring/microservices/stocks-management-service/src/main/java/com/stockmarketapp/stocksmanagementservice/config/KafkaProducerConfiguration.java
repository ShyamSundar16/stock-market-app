package com.stockmarketapp.stocksmanagementservice.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.stockmarketapp.stocksmanagementservice.model.Company;
import com.stockmarketapp.stocksmanagementservice.model.Stock;

@EnableKafka
@Configuration
public class KafkaProducerConfiguration {
    @Bean
	public Map<String, Object> companyProducerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configMap.put(ProducerConfig.RETRIES_CONFIG,"2000");
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.stockmarketapp.stocksmanagementservice.config.CompanySerializer");
		return configMap;
	}

	@Bean
	public KafkaProducer<String, Company> kafkaCompanyProducer() {
		return new KafkaProducer<String, Company>(companyProducerFactory());
	}
	
	@Bean
    public  Map<String, Object> producerStockProperties() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.RETRIES_CONFIG,"2000");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.stockmarketapp.stocksmanagementservice.config.StockSerializer");
        return configProps;
    }

    @Bean
    public KafkaProducer<String, Stock> kafkaStockProducer() {
        return new KafkaProducer<String, Stock>(producerStockProperties());
    }

   /* @Bean
    public  Map<String, String> deleteCompanyProperties() {
        Map<String, String> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.RETRIES_CONFIG,"2000");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configProps;
    }

    @Bean
    public KafkaProducer<String, String> kafkaCompanyProducer() {
        return new KafkaProducer<String, String>(deleteCompanyProperties());
    }*/
}
