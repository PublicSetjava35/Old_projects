package org.example.AccountService_A.resository;

import org.example.AccountService_A.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Устраняем проблему N + 1
    @EntityGraph(attributePaths = "inventories")
    Optional<Person> findPersonById(Integer id);
    boolean existsByEmail(String email);
}