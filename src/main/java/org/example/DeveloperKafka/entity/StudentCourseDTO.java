package org.example.DeveloperKafka.entity;
import lombok.Data;
@Data
public class StudentCourseDTO {
     private Long id;
     private String name;
     private Integer age;
     private String courseName;
     public StudentCourseDTO(String name, Integer age, String courseName) {
         this.name = name;
         this.age = age;
         this.courseName = courseName;
     }
     public StudentCourseDTO() {}

}