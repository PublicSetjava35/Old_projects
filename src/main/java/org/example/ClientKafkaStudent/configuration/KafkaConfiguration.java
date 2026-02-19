package org.example.ClientKafkaStudent.configuration;

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

    @Value("${port.kafka.start}")
    private String localhost;
    @Bean
    public NewTopic studentTopic() {
        return new NewTopic("student", 3, (short)2);
    }
    @Bean
    public NewTopic courseTopic() {
        return new NewTopic("course", 3, (short)2);
    }
    @Bean
    public NewTopic student_courseTopic() {
        return new NewTopic("topic-student-course", 3, (short)2);
    }
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, localhost);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(map);
    }
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}