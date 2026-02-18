package org.example.AccountService_A.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String email;
  @Column(name = "password")
  private String password;
  @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
  private List<Inventory> inventories;


  public Person(String email, String password) {
    this.email = email;
    this.password = password;
  }
  public Person() {}

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Inventory> getInventories() {
    return inventories;
  }

  public void setInventories(List<Inventory> inventories) {
    this.inventories = inventories;
  }
}