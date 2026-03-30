package org.example.MainApp.kafka;
import org.example.MainApp.dto.PersonDTO;
import org.example.MainApp.dto.ResourceDTO;
import org.example.MainApp.service.ControllerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumer {
    private final ObjectMapper objectMapper;
    private final ControllerService controllerService;
    public KafkaConsumer(ControllerService controllerService, ObjectMapper objectMapper) {
        this.controllerService = controllerService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "topic-person")
    public void savePerson(String message) {
        try {
            PersonDTO personDTO = objectMapper.readValue(message, PersonDTO.class);
            controllerService.savePerson(personDTO.getEmail(), personDTO.getPassword());
        } catch (Exception exception) {
            System.err.println("json bidet: " + exception.getMessage());
        }
   }
   @KafkaListener(topics = "topic-resource")
    public void saveResource(String message) {
        try {
            ResourceDTO resourceDTO = objectMapper.readValue(message, ResourceDTO.class);
            controllerService.saveResource(resourceDTO.getItem());
        } catch (Exception exception) {
            System.err.println("json bidet: " + exception.getMessage());
        }
   }
}