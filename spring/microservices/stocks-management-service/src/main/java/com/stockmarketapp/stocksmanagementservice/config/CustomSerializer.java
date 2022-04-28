package com.stockmarketapp.stocksmanagementservice.config;

import org.apache.commons.lang.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

public class CustomSerializer<T extends Serializable> implements Serializer<T> {
    public CustomSerializer(){}
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, T data) {
        return SerializationUtils.serialize(data);
    }

    @Override
    public void close() {
    }
}
