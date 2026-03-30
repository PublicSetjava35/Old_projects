package org.example.MainApp.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item")
    private String item;
    @Column(name = "timer")
    private LocalDateTime time;
    @ManyToMany(mappedBy = "resource", fetch = FetchType.LAZY)
    private Set<Person> people;

    public Resource(String item) {
        this.item = item;
    }
    @PrePersist
    public void time() {
        this.time = LocalDateTime.now();
    }
    public Resource() {}

}