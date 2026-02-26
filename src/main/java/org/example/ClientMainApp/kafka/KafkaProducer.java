package org.example.ClientMainApp.kafka;

import org.example.ClientMainApp.dto.PersonDTO;
import org.example.ClientMainApp.dto.ResourceDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducer {
        private final ObjectMapper objectMapper;
        private final KafkaTemplate<String, Object> kafkaTemplate;
        public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
            this.kafkaTemplate = kafkaTemplate;
            this.objectMapper = objectMapper;
        }
        public void person(PersonDTO personDTO) {
            String json = objectMapper.writeValueAsString(personDTO);
            kafkaTemplate.send("topic-person", json);
        }
        public void resource(ResourceDTO resourceDTO) {
           String json = objectMapper.writeValueAsString(resourceDTO);
           kafkaTemplate.send("topic-resource", json);
        }
}