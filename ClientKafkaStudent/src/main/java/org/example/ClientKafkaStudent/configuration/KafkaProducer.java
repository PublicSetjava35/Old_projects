package org.example.ClientKafkaStudent.configuration;

import org.example.ClientKafkaStudent.dto.Student;
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
   public void student(Student student) {
       String json = objectMapper.writeValueAsString(student);
       kafkaTemplate.send("student", json);
   }
   public void course(String courseName) {
       String json = objectMapper.writeValueAsString(courseName);
       kafkaTemplate.send("course", json);
   }
   public void student_course(String id) {
       String json = objectMapper.writeValueAsString(id);
       kafkaTemplate.send("topic-student-course", json);
   }
}