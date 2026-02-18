package org.example.ClientService_B.controller;

import org.example.ClientService_B.dto.ItemDTO;
import org.example.ClientService_B.dto.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    // Внедряем кафку в ручную чтобы легче поддерживать.
    // Аннотация Autowired, скрывает поля методы конструкторы, сложно поддерживать!
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    public void send(User user) {
       String json = objectMapper.writeValueAsString(user);
       kafkaTemplate.send("account", json);
    }
    public void sendItem(ItemDTO itemDTO) {
        String json = objectMapper.writeValueAsString(itemDTO);
        kafkaTemplate.send("item", json);
    }
}