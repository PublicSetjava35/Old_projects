package org.example.grains;

import jakarta.persistence.*;
import org.example.parrots.Parrot;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Grains")
public class Grains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "typeseed")
    private String typeSeed;
    @Column(name = "typeseed2")
    private String typeSeed2;
    @Column(name = "typeseed3")
    private String typeSeed3;
    @Column(name = "typeseed4")
    private String typeSeed4;
    @ManyToMany(mappedBy = "grains")
    private List<Parrot> parrotList = new ArrayList<>();

    public void addParrot(Parrot parrot) {
        parrotList.add(parrot);
    }
    public void removeParrot(Parrot parrot) {
        parrotList.remove(parrot);
    }

    public Grains() {}

    public Grains(String typeSeed, String typeSeed2, String typeSeed3, String typeSeed4) {
        this.typeSeed = typeSeed;
        this.typeSeed2 = typeSeed2;
        this.typeSeed3 = typeSeed3;
        this.typeSeed4 = typeSeed4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeSeed() {
        return typeSeed;
    }

    public void setTypeSeed(String typeSeed) {
        this.typeSeed = typeSeed;
    }

    public String getTypeSeed2() {
        return typeSeed2;
    }

    public void setTypeSeed2(String typeSeed2) {
        this.typeSeed2 = typeSeed2;
    }

    public String getTypeSeed3() {
        return typeSeed3;
    }

    public void setTypeSeed3(String typeSeed3) {
        this.typeSeed3 = typeSeed3;
    }

    public String getTypeSeed4() {
        return typeSeed4;
    }

    public void setTypeSeed4(String typeSeed4) {
        this.typeSeed4 = typeSeed4;
    }

    public List<Parrot> getParrotList() {
        return parrotList;
    }

    public void setParrotList(List<Parrot> parrotList) {
        this.parrotList = parrotList;
    }
}