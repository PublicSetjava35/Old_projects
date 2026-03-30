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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "localdatetime")
    private LocalDateTime localDateTime;
    @ManyToMany
    @JoinTable(name = "studentcourse", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courseSet = new HashSet<>();
    public void addCourse(Course course) {
        this.courseSet.add(course);
        course.getStudentSet().add(this);
    }
    public Student(String name, Integer age, LocalDateTime localDateTime) {
        this.name = name;
        this.age = age;
        this.localDateTime = localDateTime;
    }
    public Student() {}
}