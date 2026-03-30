package org.example.ClientUserKafka.kafka;

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
@PropertySource("classpath:application.properties")
public class KafkaConfiguration {
    @Value("${spring.kafka.bootstrap-servers}")
    private String port;
   @Bean
    public NewTopic newTopic() {
       return new NewTopic("registration", 2, (short)2);
   }
   @Bean
    public ProducerFactory<String, Object> producerFactory() {
       Map<String, Object> map = new HashMap<>();
       map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, port);
       map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       return new DefaultKafkaProducerFactory<>(map);
   }
   @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
       return new KafkaTemplate<>(producerFactory());
   }
}