package com.br.peti9.entities;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tutor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String surname;
private Date birth;
@OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
private List <Pet> pets;

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