package com.br.peti9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.peti9.entities.Tutor;
import com.br.peti9.repository.PetRepository;
import com.br.peti9.repository.TutorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/tutor")
public class TutorController {
    @Autowired
    TutorRepository tutorRepository;

    @PostMapping("/register")
    public void register(Tutor tutor){
        tutorRepository.save(tutor);
    }

    @GetMapping(value="/searchId/{id}")
    public Tutor getTutor (@PathVariable("id") int id) {
      return tutorRepository.findById(id).get();
    }
    

    
}
