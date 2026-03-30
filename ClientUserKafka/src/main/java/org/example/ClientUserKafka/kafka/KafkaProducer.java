package org.example.ClientUserKafka.kafka;

import org.example.ClientUserKafka.dto.UserRegistrationDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;
    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    public void output(UserRegistrationDTO dto) {
        String json = objectMapper.writeValueAsString(dto);
        kafkaTemplate.send("registration", json);
    }
}