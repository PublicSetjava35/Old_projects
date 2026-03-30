package org.example.ClientUserKafka.controller;

import org.example.ClientUserKafka.dto.UserRegistrationDTO;
import org.example.ClientUserKafka.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class KafkaMapping {
    private final KafkaProducer kafkaProducer;
    public KafkaMapping(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
   @PostMapping("/registration")
    public ResponseEntity<String> responseEntity(@RequestParam String email, @RequestParam String password, @RequestParam String passport) {
        kafkaProducer.output(new UserRegistrationDTO(email, password, passport));
        return ResponseEntity.ok("Вы успешно вошли, и сохранили паспорт");
   }

}