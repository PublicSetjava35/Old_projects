package org.example.ClientMainApp.controller;

import org.example.ClientMainApp.dto.PersonDTO;
import org.example.ClientMainApp.dto.ResourceDTO;
import org.example.ClientMainApp.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerMapping {
    private final KafkaProducer kafkaProducer;
    public ControllerMapping(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @PostMapping("/create")
    public ResponseEntity<String> responseEntity(@RequestParam String email, @RequestParam String password) {
        kafkaProducer.person(new PersonDTO(email, password));
        return ResponseEntity.ok("Вы успешно создали аккаунт!");
    }
    @PostMapping("/resource-create")
    public ResponseEntity<String> responseEntity_2(@RequestParam String item) {
        kafkaProducer.resource(new ResourceDTO(item));
        return ResponseEntity.ok("Вы добавили ресурс!");
    }
}