package com.br.peti9.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.peti9.entities.Tutor;
import com.br.peti9.services.TutorService;


@RestController
@RequestMapping(value = "/owner")
public class TutorController {

  @Autowired
  TutorService tutorService;

  @PostMapping("/register")
  public ResponseEntity<String> register(Tutor tutor) {
    if (tutorService.register(tutor)) {
      return ResponseEntity.ok("Tutor successfully saved!");
    }
    return ResponseEntity.ok("Existing tutor name!");
  }

  @GetMapping(value = "/searchById/{id}")
  public Tutor searchById(@PathVariable("id") int id) {
    return tutorService.searchById(id);
  }

  @DeleteMapping(value = "/deleteById/{id}")
  public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
    if (tutorService.deleteById(id)) 
    { return ResponseEntity.ok("Tutor successfully deleted");
    
    }
     return ResponseEntity.ok("Tutor not found");
    
  }

  @GetMapping(value = "/list")
  public List<Tutor> getListTutor() {
    return tutorService.getListTutor();
  }

  @PutMapping("/updateByName/{name}")
  public ResponseEntity<String> updateTutorByName(@PathVariable String name, @RequestBody Tutor updatedTutor) {
    if (tutorService.updateTutorByName(name, updatedTutor)) {
      return ResponseEntity.ok("Tutor updated successfully.");
    } 
      return ResponseEntity.notFound().build();
  }

  @GetMapping(value = "/searchByName/{name}")
  public List<Tutor> searchTutorsByName(@PathVariable("name") String name) {
    return tutorService.searchTutorsByName(name);
  }

}
