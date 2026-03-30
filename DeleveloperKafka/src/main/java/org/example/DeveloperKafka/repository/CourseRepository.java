package org.example.DeveloperKafka.repository;

import org.example.DeveloperKafka.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT s FROM Course s JOIN FETCH s.studentSet WHERE s.id = :id")
    Optional<Course> findAllById(Long id);
    Set<Course> findCourseByIdIs(Long id);
}
