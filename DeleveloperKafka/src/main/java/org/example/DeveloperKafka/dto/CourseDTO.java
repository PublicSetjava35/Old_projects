package org.example.DeveloperKafka.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CourseDTO {
     private Long id;
     private String courseName;
     private LocalDateTime localDateTime;
     public CourseDTO(String courseName, LocalDateTime localDateTime) {
         this.courseName = courseName;
         this.localDateTime = localDateTime;
     }
     public CourseDTO() {}
}