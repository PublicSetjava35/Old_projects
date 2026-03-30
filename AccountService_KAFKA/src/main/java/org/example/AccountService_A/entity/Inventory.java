package org.example.AccountService_A.entity;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "item")
    private String item;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Inventory(String item, Person person) {
        this.item = item;
        this.person = person;
    }
    public Inventory() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}