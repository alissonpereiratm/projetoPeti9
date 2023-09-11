package com.br.peti9.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.peti9.entities.Pet;
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
  public ResponseEntity<String> register(Tutor tutor) {
    List<Tutor> tutors = tutorRepository.findAll();
    boolean tutorExists = tutors.stream().anyMatch(p -> p.getName().equals(tutor.getName()));
    if (tutorExists) {
      return ResponseEntity.ok("Existing tutor name!");
    } else {
      tutorRepository.save(tutor);
      return ResponseEntity.ok("Tutor successfully saved!");
    }
  }

  @GetMapping(value = "/searchId/{id}")
  public Tutor getTutor(@PathVariable("id") int id) {
    return tutorRepository.findById(id).get();
  }

  @GetMapping(value = "/deleteId/{id}")
  public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
    Optional<Tutor> tutor = tutorRepository.findById(id);
    if (tutor.isPresent()) {
      tutorRepository.deleteById(id);
      return ResponseEntity.ok("Tutor successfully deleted");
    } else {
      return ResponseEntity.ok("Tutor not found");
    }
  }

}
