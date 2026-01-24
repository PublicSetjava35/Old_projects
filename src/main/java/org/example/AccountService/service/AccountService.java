package org.example.AccountService.service;

import org.example.AccountService.basedb.Account;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final HibernateDB hibernateDB;

    public AccountService(HibernateDB hibernateDB) {
        this.hibernateDB = hibernateDB;
    }

    public void saveBase(String email, String password, LocalDateTime time) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(email, password, time, 500_000, 0));
        hibernateDB.saveAll(accounts);
    }

    public LocalDateTime time() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}