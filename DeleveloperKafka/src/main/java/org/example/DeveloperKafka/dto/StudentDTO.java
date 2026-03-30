package org.example.DeveloperKafka.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StudentDTO {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime localDateTime;
    public StudentDTO(String name, Integer age, LocalDateTime localDateTime) {
        this.age = age;
        this.name = name;
        this.localDateTime = localDateTime;
    }
    public StudentDTO() {}
}