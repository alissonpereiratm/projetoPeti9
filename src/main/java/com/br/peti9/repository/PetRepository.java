package com.br.peti9.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.peti9.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<Pet> findByName(String nome);

    @Query("SELECT p FROM Pet p WHERE p.name ILIKE %:name%")
List<Pet> findByNameContaining(@Param("name") String name);

}
