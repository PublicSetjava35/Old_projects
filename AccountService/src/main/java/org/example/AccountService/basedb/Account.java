package org.example.AccountService.basedb;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Account", indexes = @Index(columnList = "email", unique = true))
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "date_time")
    private LocalDateTime time;
    @Column(name = "balance")
    private Integer balance;
    @Column(name = "ticket")
    private Integer ticket;

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Account(String email, String password, LocalDateTime time, Integer balance, Integer ticket) {
        this.password = password;
        this.email = email;
        this.time = time;
        this.balance = balance;
        this.ticket = ticket;
    }

    public Account() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}