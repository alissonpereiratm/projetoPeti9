package com.br.peti9.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.peti9.dto.PetDto;
import com.br.peti9.model.Pet;
import com.br.peti9.model.Tutor;
import com.br.peti9.repository.PetRepository;
import com.br.peti9.repository.TutorRepository;

import jakarta.persistence.EntityNotFoundException;

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

    public PetDto getPet(int id) {
        Optional<Pet> petOptional = petRepository.findById(id);

        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            return new PetDto(pet);
        } else {
            throw new EntityNotFoundException("Pet not found");
        }
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

    public List<PetDto> getListPet() {
        return petRepository.findAll().stream().map(PetDto::new).collect(Collectors.toList());
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

    public List<PetDto> searchPetsByName(String name) {
        return petRepository.findByNameContaining(name).stream().map(PetDto::new).collect(Collectors.toList());
    }

}
