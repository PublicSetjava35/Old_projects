package org.example.AccountService.service;

import org.example.AccountService.basedb.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HibernateDB extends JpaRepository<Account, Integer> {

}
