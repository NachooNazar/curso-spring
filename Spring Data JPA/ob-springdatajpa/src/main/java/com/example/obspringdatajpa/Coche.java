package com.example.obspringdatajpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coche {

    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Getter @Setter
    private Integer yearBuild;

    @Getter @Setter
    private String manuFacturer;

    @Getter @Setter
    private String model;

    public Coche() {

    }

    public Coche(Long id, Integer year, String manuFacturer, String model) {
        this._id = id;
        this.yearBuild = year;
        this.manuFacturer = manuFacturer;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + _id +
                ", year=" + yearBuild +
                ", manuFacturer='" + manuFacturer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
