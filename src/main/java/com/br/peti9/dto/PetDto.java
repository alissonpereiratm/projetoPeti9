package com.br.peti9.dto;

import com.br.peti9.model.Pet;
import com.br.peti9.model.Tutor;

public class PetDto extends BaseEntityDto {
    
    private String name;
    private String breed;
    private Tutor tutor;



    public PetDto(Pet pet) {
        this.id=pet.getId();
        this.name = pet.getName();
        this.tutor=pet.getTutor();
    }
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public Tutor getTutor() {
        return tutor;
    }
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    
}
