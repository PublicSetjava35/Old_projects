package org.example.DeveloperKafka.consumer;

import org.example.DeveloperKafka.service.ControllerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private final ControllerService controllerService;
    public KafkaConsumer(ControllerService controllerService) {
        this.controllerService = controllerService;
    }
    @KafkaListener(topics = "student")
    public void save(String message) {
        try {
            controllerService.saveStudent(message);
        } catch (Exception exception) {
            System.err.println("error json bidet: " + exception.getMessage());
        }
    }
    @KafkaListener(topics = "course")
    public void course(String courseName) {
        try {
            controllerService.saveCourse(courseName);
        } catch (Exception exception) {
            System.err.println("error json bidet: " + exception.getMessage());
        }
    }
    @KafkaListener(topics = "topic-student-course")
    public void saveStudentCourse(String id) {
        try {
            controllerService.saveStudentCourse(id);
        } catch (Exception exception) {
            System.err.println("error json bidet: " + exception.getMessage());
        }
    }
}