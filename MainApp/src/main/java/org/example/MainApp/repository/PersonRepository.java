package org.example.MainApp.repository;

import org.example.MainApp.entity.Person;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @NullMarked
    @Query("SELECT l FROM Person l LEFT JOIN FETCH l.resource WHERE l.id = :id")
    Optional<Person> findById(@Param("id") Long id);
    boolean existsByEmail(String email);
}