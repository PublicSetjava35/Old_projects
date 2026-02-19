package org.example.DeveloperKafka.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "coursename")
    private String courseName;
    @Column(name = "localdatetime")
    private LocalDateTime localDateTime;
    @ManyToMany(mappedBy = "courseSet")
    private Set<Student> studentSet = new HashSet<>();
    public Course(String courseName, LocalDateTime localDateTime) {
        this.courseName = courseName;
        this.localDateTime = localDateTime;
    }
    public Course() {}
}