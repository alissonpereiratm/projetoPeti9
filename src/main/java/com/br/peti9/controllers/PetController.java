package com.br.peti9.controllers;

import java.util.List;

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
import com.br.peti9.services.PetService;

@Controller
@RestController
@RequestMapping(value = "/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping("/register")
    public ResponseEntity<String> register(Pet pet) {
        return switch (petService.register(pet)) {
            case 1 -> ResponseEntity.ok("Existing pet name!");
            case 2 -> ResponseEntity.ok("Pet saved successfully!");
            default -> ResponseEntity.ok("Tutor not found!");
        };

    }

    @GetMapping(value = "/searchById/{id}")
    public Pet getPet(@PathVariable("id") int id) {
        return petService.getPet(id);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        if (petService.deleteById(id)) {
            return ResponseEntity.ok("Pet successfully deleted");
        } else {
            return ResponseEntity.ok("Pet not found");
        }
    }

    @GetMapping(value = "/list")
    public List<Pet> getListPet() {
        return petService.getListPet();
    }

    @PutMapping("/updateByName/{name}")
    public ResponseEntity<String> updatePetByName(@PathVariable String name, @RequestBody Pet updatePet) {
        if (petService.updatePetByName(name, updatePet)) {
            return ResponseEntity.ok("Pet updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<String> updatePetById(@PathVariable int id, @RequestBody Pet updatePet) {
        if (petService.deleteById(id)) {
            return ResponseEntity.ok("Pet updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/searchByName/{name}")
    public List<Pet> searchPetsByName(@PathVariable("name") String name) {
        return petService.searchPetsByName(name);
    }

}
