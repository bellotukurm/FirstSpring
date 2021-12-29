package com.example.demo.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository
        extends JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p WHERE p.email = ?1")
    Optional<Pet> findPetByEmail(String email);
}
