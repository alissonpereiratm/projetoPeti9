package com.br.peti9.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.peti9.entities.Tutor;
import com.br.peti9.repository.TutorRepository;

@Service
public class TutorService  {

    @Autowired
    TutorRepository tutorRepository;

    public boolean register(Tutor tutor) {
        List<Tutor> tutors = tutorRepository.findAll();
        boolean tutorExists = tutors.stream().anyMatch(p -> p.getName().equals(tutor.getName()));
        if (tutorExists) {
            return false;
        } else {
            tutorRepository.save(tutor);
            return true;
        }
    }

    public boolean updateTutorByName(String name, Tutor updatedTutor) {
        Optional<Tutor> existingTutorOptional = tutorRepository.findByName(name);

        if (existingTutorOptional.isPresent()) {
            Tutor existingTutor = existingTutorOptional.get();
            existingTutor.setName(updatedTutor.getName());
            existingTutor.setSurname(updatedTutor.getSurname());
            existingTutor.setBirth(updatedTutor.getBirth());
            tutorRepository.save(existingTutor);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteById(int id) {
        Optional<Tutor> tutor = tutorRepository.findById(id);
        if (tutor.isPresent()) {
            tutorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Tutor getTutor(int id) {
        return tutorRepository.findById(id).get();
    }

    public List<Tutor> getListTutor() {
        return tutorRepository.findAll();
    }

    public List<Tutor> searchTutorsByName(String name) {
        return tutorRepository.findByNameContaining(name);
    }

}
