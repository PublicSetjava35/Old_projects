package org.example.parrots;

import jakarta.persistence.*;
import org.example.grains.Grains;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Parrot")
public class Parrot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nameparrot")
    private String nameParrot;
    @Column(name = "ate")
    private Integer ate;
    @Column(name = "leftfoot")
    private boolean leftFoot;
    @Column(name = "rightfoot")
    private boolean rightFoot;
    @ManyToMany
    @JoinTable(name = "Grains_Parrot",
            joinColumns = @JoinColumn(name = "ownerparrot"),
            inverseJoinColumns = @JoinColumn(name = "ownergrains"))
    private List<Grains> grains = new ArrayList<>();

    public void addGrains(Grains grain) {
        grains.add(grain);
    }
    public void removeGrains(Grains grain) {
        grains.remove(grain);
    }

    public Parrot() {}

    public Parrot(String nameParrot, Integer ate, boolean leftFoot, boolean rightFoot) {
        this.nameParrot = nameParrot;
        this.ate = ate;
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameParrot() {
        return nameParrot;
    }

    public void setNameParrot(String nameParrot) {
        this.nameParrot = nameParrot;
    }

    public Integer getAte() {
        return ate;
    }

    public void setAte(Integer ate) {
        this.ate = ate;
    }

    public boolean isLeftFoot() {
        return leftFoot;
    }

    public void setLeftFoot(boolean leftFoot) {
        this.leftFoot = leftFoot;
    }

    public boolean isRightFoot() {
        return rightFoot;
    }

    public void setRightFoot(boolean rightFoot) {
        this.rightFoot = rightFoot;
    }

    public List<Grains> getGrains() {
        return grains;
    }

    public void setGrains(List<Grains> grains) {
        this.grains = grains;
    }
}