package com.br.peti9.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.peti9.entities.Pet;

public interface PetRepository extends JpaRepository<Pet,Integer> {
    
}