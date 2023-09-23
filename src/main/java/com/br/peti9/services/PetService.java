package com.br.peti9.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.peti9.entities.Pet;
import com.br.peti9.entities.Tutor;
import com.br.peti9.repository.PetRepository;
import com.br.peti9.repository.TutorRepository;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    TutorRepository tutorRepository;

    public int register(Pet pet) {
        try {
            if (pet.getTutor() == null) {
                throw new IllegalArgumentException();
            }
            Optional<Tutor> tutorOptional = tutorRepository.findById(pet.getTutor().getId());
            Tutor tutor = tutorOptional.get();
            List<Pet> petTutorList = tutor.getPets();
            boolean petExists = petTutorList.stream().anyMatch(p -> p.getName().equals(pet.getName()));

            if (petExists) {
                return 1;
            } else {
                petRepository.save(pet);
                return 2;
            }
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    public Pet getPet(int id) {
        return petRepository.findById(id).get();
    }

    public boolean deleteById(int id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            petRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Pet> getListPet() {
        return petRepository.findAll();
    }

    public boolean updatePetByName(String name, Pet updatePet) {
        Optional<Pet> existingPetOptional = petRepository.findByName(name);

        if (existingPetOptional.isPresent()) {
            Pet existingPet = existingPetOptional.get();
            existingPet.setName(updatePet.getName());
            existingPet.setBreed(updatePet.getBreed());
            existingPet.setBirth(updatePet.getBirth());
            existingPet.setColor(updatePet.getColor());
            existingPet.setWeight(updatePet.getWeight());
            existingPet.setVaccineDate(updatePet.getVaccineDate());
            existingPet.setVaccineType(updatePet.getVaccineType());
            petRepository.save(existingPet);
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePetById(int id, Pet updatePet) {
        Optional<Pet> existingPetOptional = petRepository.findById(id);
        if (existingPetOptional.isPresent()) {
            Pet existingPet = existingPetOptional.get();
            existingPet.setName(updatePet.getName());
            existingPet.setBreed(updatePet.getBreed());
            existingPet.setBirth(updatePet.getBirth());
            existingPet.setColor(updatePet.getColor());
            existingPet.setWeight(updatePet.getWeight());
            existingPet.setVaccineDate(updatePet.getVaccineDate());
            existingPet.setVaccineType(updatePet.getVaccineType());
            petRepository.save(existingPet);
            return true;
        } else {
            return false;
        }
    }

    public List<Pet> searchPetsByName(String name) {
        return petRepository.findByNameContaining(name);
    }
}
