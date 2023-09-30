package com.br.peti9.dto;

import com.br.peti9.model.Tutor;

public class TutorDto extends BaseEntityDto {

    private String name;
    private String surname;

    public TutorDto(Tutor tutor) {
        this.id = tutor.getId();
        this.name = tutor.getName();
        this.surname = tutor.getSurname();
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

}
