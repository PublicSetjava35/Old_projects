package org.example.MainApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "timer")
    private LocalDateTime time;
    @ManyToMany
    @JoinTable(name = "personresource",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "resources_id"))
    private Set<Resource> resource;
    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }
    @PrePersist
    public void time() {
        this.time = LocalDateTime.now();
    }
    public Person() {}


}