package com.example.demo.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public void addNewPet(Pet pet){
        Optional<Pet> petOptional = petRepository
                .findPetByEmail(pet.getEmail());
        if (petOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        petRepository.save(pet);
    }

    public void deletePet(Long petId){
        boolean exists = petRepository.existsById(petId);
        if(!exists) {
            throw new IllegalStateException(
                    "pet with id " + petId + " does not exist");
        }
        petRepository.deleteById(petId);
    }

    @Transactional
    public void updatePet(Long petId,
                          String name,
                          String email){
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new IllegalStateException(
                "pet with id " + petId + " does not exist"
        ));

        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(pet.getName(), name)) {
            pet.setName(name);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(pet.getEmail(), email)) {
            Optional<Pet> petOptional = petRepository
                    .findPetByEmail(email);
            if (petOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            pet.setEmail(email);
        }
    }
}
