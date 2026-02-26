package org.example.MainApp.repository;

import org.example.MainApp.entity.Resource;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @NullMarked
    @Query("SELECT l FROM Resource l LEFT JOIN FETCH l.people WHERE l.id = :id")
    Optional<Resource> findById(@Param("id") Long aLong);
}
