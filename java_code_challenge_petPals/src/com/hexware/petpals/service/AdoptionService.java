package com.hexware.petpals.service;

import com.hexaware.petpals.entity.model.*;
import com.hexaware.petpals.exception.*;

public class AdoptionService {
    private PetShelter shelter;

    public AdoptionService(PetShelter shelter) {
        this.shelter = shelter;
    }

    public void adoptPet(Pet pet) throws AdoptionException {
        if (!shelter.listAvailablePets().contains(pet)) {
            throw new AdoptionException("The selected pet is not available for adoption.");
        }
        shelter.removePet(pet);
        System.out.println("Congratulations! You have adopted " + pet.getName() + ".");
    }
}