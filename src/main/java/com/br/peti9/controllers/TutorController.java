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

import com.br.peti9.entities.Tutor;
import com.br.peti9.repository.TutorRepository;

@Controller
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

  @DeleteMapping(value = "/deleteId/{id}")
  public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
    Optional<Tutor> tutor = tutorRepository.findById(id);
    if (tutor.isPresent()) {
      tutorRepository.deleteById(id);
      return ResponseEntity.ok("Tutor successfully deleted");
    } else {
      return ResponseEntity.ok("Tutor not found");
    }
  }

  @GetMapping(value = "/list")
  public List<Tutor> getListTutor() {
    return tutorRepository.findAll();
  }

  @PutMapping("/{name}")
  public ResponseEntity<String> updateTutorByName(@PathVariable String name, @RequestBody Tutor updatedTutor) {
    Optional<Tutor> existingTutorOptional = tutorRepository.findByName(name);

    if (existingTutorOptional.isPresent()) {
      Tutor existingTutor = existingTutorOptional.get();
      existingTutor.setName(updatedTutor.getName());
      existingTutor.setSurname(updatedTutor.getSurname());
      existingTutor.setBirth(updatedTutor.getBirth());
      tutorRepository.save(existingTutor);
      return ResponseEntity.ok("Tutor updated successfully.");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
