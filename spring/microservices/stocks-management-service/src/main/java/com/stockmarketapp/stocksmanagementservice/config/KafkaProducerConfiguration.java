package com.stockmarketapp.stocksmanagementservice.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import com.stockmarketapp.stocksmanagementservice.model.Company;
import com.stockmarketapp.stocksmanagementservice.model.Stock;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public ProducerFactory<String, Company> addCompanyProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.stockmarketapp.stocksmanagementservice.config.CompanySerializer");

        return new DefaultKafkaProducerFactory<String, Company>(config);
    }

    @Bean
    public KafkaTemplate<String, Company> addCompanyKafkaTemplate() {
        return new KafkaTemplate<>(addCompanyProducerFactory());
    }

    @Bean
    public ProducerFactory<String, Stock> addStockProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.stockmarketapp.stocksmanagementservice.config.StockSerializer");

        return new DefaultKafkaProducerFactory<String, Stock>(config);
    }

    @Bean
    public KafkaTemplate<String, Stock> addStockKafkaTemplate() {
        return new KafkaTemplate<>(addStockProducerFactory());
    }

    @Bean
    public ProducerFactory<String, String> deleteCompanyProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<String, String>(config);
    }

    @Bean
    public KafkaTemplate<String, String> deleteCompanyKafkaTemplate() {
        return new KafkaTemplate<>(deleteCompanyProducerFactory());
    }

    /*
    @Bean
	public Map<String, Object> addCompanyProducerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configMap.put(ProducerConfig.RETRIES_CONFIG,"2000");
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.stockmarketapp.stocksmanagementservice.config.CompanySerializer");
		return configMap;
	}

	@Bean
	public KafkaProducer<String, Company> kafkaCompanyProducer() {
		return new KafkaProducer<String, Company>(addCompanyProducerFactory());
	}

	@Bean
    public  Map<String, Object> addStockProducerProperties() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.RETRIES_CONFIG,"2000");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.stockmarketapp.stocksmanagementservice.config.StockSerializer");
        return configProps;
    }

    @Bean
    public KafkaProducer<String, Stock> kafkaStockProducer() {
        return new KafkaProducer<String, Stock>(addStockProducerProperties());
    }

    @Bean
    public  Map<String, Object> deleteCompanyProducerProperties() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.RETRIES_CONFIG,"2000");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configProps;
    }

    @Bean
    public KafkaProducer<String, String> kafkaDeleteCompanyProducer() {
        return new KafkaProducer<String, String>(deleteCompanyProducerProperties());
    }*/
 /*
    @Bean
    private static Properties getKafkaProducerConfig(){
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, "stock-market-app");
        return props;
    }

   @Bean
    public static KafkaProducer<String, Company> addCompanyProducer(){
        KafkaProducer<String, Company> companyKafkaProducer=new KafkaProducer<String,Company>(getKafkaProducerConfig());
        return companyKafkaProducer;
    }

    @Bean
    public static KafkaProducer<String, Stock> addStockProducer(){
        KafkaProducer<String, Stock> stockKafkaProducer=new KafkaProducer<String,Stock>(getKafkaProducerConfig());
        return stockKafkaProducer;
    }

    @Bean
    public static KafkaProducer<String, String> deleteCompanyProducer(){
        KafkaProducer<String, String> deleteCompanyProducer=new KafkaProducer<String,String>(getKafkaProducerConfig());
        return deleteCompanyProducer;
    }*/
}
