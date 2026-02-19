package org.example.DeveloperKafka.repository;

import org.example.DeveloperKafka.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT t FROM Student t LEFT JOIN FETCH t.courseSet WHERE t.id = :id")
    Optional<Student> findAllById(Long id);
    Set<Student> findStudentById(Long id);
}
