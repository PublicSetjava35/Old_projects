package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "password")
    private int password;
    @Column(name = "isValid")
    private boolean isValid;

    public Person() {/* null */}

    public Person(String user_name, int password, boolean isValid) {
        this.user_name = user_name;
        this.password = password;
        this.isValid = isValid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getPassword() {
        return password;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password=" + password +
                ", isValid=" + isValid +
                '}';
    }
}