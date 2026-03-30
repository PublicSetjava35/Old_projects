package org.example.DeveloperKafka.service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.example.DeveloperKafka.dto.StudentDTO;
import org.example.DeveloperKafka.entity.Course;
import org.example.DeveloperKafka.entity.Student;
import org.example.DeveloperKafka.repository.CourseRepository;
import org.example.DeveloperKafka.repository.StudentRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

@Service
public class ControllerService {
     private final CourseRepository courseRepository;
     private final StudentRepository studentRepository;
     private final ObjectMapper objectMapper;
     public ControllerService(CourseRepository courseRepository, StudentRepository studentRepository, ObjectMapper objectMapper) {
         this.courseRepository = courseRepository;
         this.studentRepository = studentRepository;
         this.objectMapper = objectMapper;
     }
     @Transactional
     public void saveStudent(String message) {
         StudentDTO studentDTO = objectMapper.readValue(message, StudentDTO.class);
         if(studentDTO.getName() == null || studentDTO.getName().isBlank()) {
             throw new IllegalArgumentException("Name required!");
         }
         if(studentDTO.getAge() == null || studentDTO.getAge() < 0 || studentDTO.getAge() < 8) {
             throw new IllegalArgumentException("The required age must not be empty or negative.");
         }
         studentRepository.save(new Student(studentDTO.getName(), studentDTO.getAge(), LocalDateTime.now()));
     }
     @Transactional
     public void saveCourse(String courseName) {
         String course = objectMapper.readValue(courseName, String.class);
         if(course == null || course.isBlank()) {
             throw new IllegalArgumentException("Error, not found course");
         }
         courseRepository.save(new Course(courseName, LocalDateTime.now()));
     }
     @Transactional
     public void saveStudentCourse(String id) {
         String idDOT = objectMapper.readValue(id, String.class);
         Long idL = Long.parseLong(idDOT);
         Student student = studentRepository.findAllById(idL)
                 .orElseThrow(() -> new EntityExistsException("Ошибка, студент не найден!"));
         Course course = courseRepository.findAllById(idL)
                 .orElseThrow(() -> new EntityExistsException("Ошибка, курсы не найдены!"));
         student.addCourse(course);
         studentRepository.save(student);
     }
}