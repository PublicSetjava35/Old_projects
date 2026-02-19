package org.example.ClientKafkaStudent.mapping;

import lombok.NonNull;
import org.example.ClientKafkaStudent.configuration.KafkaProducer;
import org.example.ClientKafkaStudent.dto.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClassController {
    private final KafkaProducer kafkaProducer;
    public ClassController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @PostMapping("/student")
    public ResponseEntity<String> responseEntity(@RequestParam @NonNull String name, @RequestParam Integer age) {
        kafkaProducer.student(new Student(name, age));
        return ResponseEntity.ok("Вы успешно были сохранены!");
    }
    @PostMapping("/course")
    public ResponseEntity<String> responseEntityCourse(@RequestParam String courseName) {
        kafkaProducer.course(courseName);
        return ResponseEntity.ok("Ваш курс был успешно сохранен!");
    }
    @PostMapping("/student-course")
    public ResponseEntity<String> responseEntityStudentCourse(@RequestParam String id) {
        kafkaProducer.student_course(id);
        return ResponseEntity.ok("Ваши записи были успешно сохранены!");
    }
}