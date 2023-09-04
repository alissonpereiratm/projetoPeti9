package com.br.peti9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.peti9.entities.Pet;
import com.br.peti9.repository.PetRepository;

@RestController
@RequestMapping(value = "/pet")
public class PetController {
    @Autowired
    PetRepository petRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(Pet pet) {

        if (true) {
            petRepository.save(pet);
            return ResponseEntity.ok("Pet salvo com sucesso!");
        } else {
            return ResponseEntity.ok("Nome de pet ja existente!");
        }
    }

    @GetMapping(value = "/searchId/{id}")
    public Pet getPet(@PathVariable("id") int id) {
        return petRepository.findById(id).get();
    }
}
