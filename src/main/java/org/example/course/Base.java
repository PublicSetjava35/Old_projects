package org.example.course;

import jakarta.persistence.*;

@Entity
@Table(name = "List")
public class Base {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "keys")
    private String keys;
    @Column(name = "level")
    private int level;

    public Base(){}

    public Base(String name, int age, String keys, int level) {
        this.name = name;
        this.age = age;
        this.keys = keys;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", keys='" + keys + '\'' +
                ", level=" + level +
                '}';
    }
}