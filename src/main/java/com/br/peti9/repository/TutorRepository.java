package com.br.peti9.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.peti9.entities.Tutor;

public interface TutorRepository extends JpaRepository<Tutor,Integer>{
    Optional<Tutor> findByName(String nome);
}
