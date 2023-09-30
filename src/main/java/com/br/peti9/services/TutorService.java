package com.br.peti9.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.peti9.dto.TutorDto;
import com.br.peti9.model.Tutor;
import com.br.peti9.repository.TutorRepository;

import jakarta.persistence.EntityNotFoundException;

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

    public TutorDto searchById(int id) {
    Optional<Tutor> tutorOptional = tutorRepository.findById(id);
    
    if (tutorOptional.isPresent()) {
        Tutor tutor = tutorOptional.get();
        return new TutorDto(tutor);
    } else {
        throw new EntityNotFoundException("Tutor not found");
    }
}

    public List<TutorDto> getListTutor() {
        return tutorRepository.findAll().stream().map(TutorDto::new).collect(Collectors.toList());
    }

    public List<Tutor> searchTutorsByName(String name) {
        return tutorRepository.findByNameContaining(name);
    }

}
