package com.example.firstSB.config;

import com.example.firstSB.model.Employee;
import nonapi.io.github.classgraph.json.JSONSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration //these clases will be executed during server setup
public class KafkaProducerConfiguration {

    @Bean
    public ProducerFactory<String, Employee> producerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JSONSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG,"1");
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 25);
        return new DefaultKafkaProducerFactory<>(config);

    }

    @Bean
    public KafkaTemplate<String, Employee> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
