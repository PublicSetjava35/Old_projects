package org.example.ClientService_B.mapping;

import org.example.ClientService_B.controller.KafkaProducer;
import org.example.ClientService_B.dto.ItemDTO;
import org.example.ClientService_B.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClintController {
    private final KafkaProducer kafkaProducer;
    public ClintController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/create_account")
    public ResponseEntity<String> responseEntity(@RequestParam String email, @RequestParam String password) {
        kafkaProducer.send(new User(email, password));
        return ResponseEntity.ok("Вы успешно создали аккаунт!");
    }

    @PostMapping("/create_item")
    public ResponseEntity<String> responseEntityItem(@RequestParam Integer id, @RequestParam String item) {
        kafkaProducer.sendItem(new ItemDTO(id, item));
        return ResponseEntity.ok("Вы успешно добавили предмет!");
    }
}