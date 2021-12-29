package com.example.demo.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pet")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> getPets() {
        return petService.getPets();
    }

    @PostMapping
    public void registerNewPet(@RequestBody Pet pet){
        petService.addNewPet(pet);
    }

    @DeleteMapping(path = "{petId}")
    public void deletePet(@PathVariable("petId") Long petId){
        petService.deletePet(petId);
    }

    @PutMapping(path = "{petId}")
    public void updatePet(
            @PathVariable("petId") Long petId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        petService.updatePet(petId, name, email);
    }
}
