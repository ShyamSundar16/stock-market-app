package com.stockmarketapp.searchstocksservice.config;

import java.util.HashMap;
import java.util.Map;

import com.stockmarketapp.searchstocksservice.model.Company;
import com.stockmarketapp.searchstocksservice.model.Stock;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, Company> companyConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, CompanyDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.stockmarketapp.searchstocksservice.config.CompanyDeserializer");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Company.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Company> companyKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Company> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(companyConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Stock> stockConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StockDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.stockmarketapp.searchstocksservice.config.StockDeserializer");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Stock.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Stock> stockKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Stock> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> deleteCompanyConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StockDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StockDeserializer.class );
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> deleteCompanyKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(deleteCompanyConsumerFactory());
        return factory;
    }
/*
    @Bean
    private static Properties getKafkaConsumerConfig(){
        Properties consumerProps = new Properties();
        //consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, "stock-market-app");
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "Sample-grp_id");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return consumerProps;
    }

    @Bean
    public static KafkaConsumer<String,Company> addCompanyConsumer(){
        KafkaConsumer<String, Company> addCompanyConsumer=new KafkaConsumer<String,Company>(getKafkaConsumerConfig());
        return addCompanyConsumer;
    }

    @Bean
    public static KafkaConsumer<String,Stock> addStockConsumer(){
        KafkaConsumer<String, Stock> addStockConsumer=new KafkaConsumer<String,Stock>(getKafkaConsumerConfig());
        return addStockConsumer;
    }

    @Bean
    public static KafkaConsumer<String,String> deleteCompanyConsumer(){
        KafkaConsumer<String, String> deleteCompanyConsumer=new KafkaConsumer<String,String>(getKafkaConsumerConfig());
        return deleteCompanyConsumer;
    }*/

}
