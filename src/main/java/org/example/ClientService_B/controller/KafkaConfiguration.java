package org.example.ClientService_B.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.yml")
public class KafkaConfiguration {
    @Value("${kafka.boot.strap}")
    private String port;

    @Bean
    public NewTopic newTopic() {
        return new NewTopic("account", 1, (short)1);
    }
    @Bean
    public NewTopic itemTopic() {
        return new NewTopic("item", 1, (short)1);
    }
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, port);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(map);
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}