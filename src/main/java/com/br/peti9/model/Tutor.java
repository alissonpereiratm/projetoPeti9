package com.br.peti9.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Past;

@Entity
public class Tutor extends BaseEntity {

    private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    @Past(message = "The date of birth must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    private List<Pet> pets;

    public Tutor() {
    }

    public Tutor(int id, String name, String surname, Date birth, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.pets = pets;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
