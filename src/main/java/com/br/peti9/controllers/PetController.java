package com.br.peti9.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.peti9.entities.Pet;
import com.br.peti9.entities.Tutor;
import com.br.peti9.repository.PetRepository;
import com.br.peti9.repository.TutorRepository;

@Controller
@RestController
@RequestMapping(value = "/pet")
public class PetController {
    @Autowired
    PetRepository petRepository;
    @Autowired
    TutorRepository tutorRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(Pet pet) {
        String petName = pet.getName();
        Optional<Tutor> tutorPet = tutorRepository.findById(pet.getTutor().getId());

        if (tutorPet.isPresent()) {
            Tutor tutor = tutorPet.get();
            List<Pet> petTutor = tutor.getPets();
            boolean petExists = petTutor.stream().anyMatch(p -> p.getName().equals(petName));

            if (petExists) {
                return ResponseEntity.ok("Existing pet name!");
            } else {
                petRepository.save(pet);
                return ResponseEntity.ok("Pet saved successfully!");
            }
        } else {
            return ResponseEntity.ok("Tutor not found!");
        }
    }

    @GetMapping(value = "/searchId/{id}")
    public Pet getPet(@PathVariable("id") int id) {
        return petRepository.findById(id).get();
    }

    @DeleteMapping(value = "/deleteId/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            petRepository.deleteById(id);
            return ResponseEntity.ok("Pet excluido com sucesso");
        } else {
            return ResponseEntity.ok("Pet não encontrado");
        }
    }

    @GetMapping(value = "/list")
    public List<Pet> getListPet() {
        return petRepository.findAll();
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateTutorByName(@PathVariable String name, @RequestBody Pet updatePet) {
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
            return ResponseEntity.ok("Pet updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/searchByName/{name}")
    public List<Pet> searchPetsByName(@PathVariable("name") String name) {
        return petRepository.findByNameContaining(name);
    }

}
