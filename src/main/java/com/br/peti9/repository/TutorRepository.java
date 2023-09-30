package com.br.peti9.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.peti9.model.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    Optional<Tutor> findByName(String nome);

    @Query("SELECT t FROM Tutor t WHERE t.name ILIKE %:name%")
    List<Tutor> findByNameContaining(@Param("name") String name);

}
