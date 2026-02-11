package org.example.DiaryService.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryJPA extends JpaRepository<Users, Integer> {
       Optional<Users> findUsersByToken(String token);
}
