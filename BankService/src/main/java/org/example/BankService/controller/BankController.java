package org.example.BankService.controller;

import org.example.BankService.classesDTO.Account;
import org.example.BankService.classesDTO.AccountDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class BankController {


    @PostMapping("/bank")
    public Map<String, Object> resultBalance(@RequestBody AccountDTO accountDTO) {
        Configuration configuration = new Configuration().addAnnotatedClass(Account.class);
        configure(configuration);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction(); // открываем транзакцию
            Account account = session.createQuery("FROM Account a WHERE a.email = :email", Account.class).setParameter("email", accountDTO.getEmail()).uniqueResult();
            session.merge(account);
            tx.commit();
            return Map.of("ok_balance", true, "balance", account.getBalance());
        }
    }

    public void configure(Configuration configuration) {
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/mydb");
        configuration.setProperty("hibernate.connection.username", "admin");
        configuration.setProperty("hibernate.connection.password", "45085");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
    }
}