package com.hexware.petpals.service;

import com.hexaware.petpals.dao.IPetDao; 
import com.hexaware.petpals.entity.model.Pet;
import com.hexaware.petpals.exception.InvalidPetAgeException;

import java.sql.SQLException;
import java.util.List;

public class PetService {
    private IPetDao petDao; 
    
    public PetService(IPetDao petDAO) { 
        this.petDao = petDAO;
    }

 
    public void addPet(Pet pet) throws InvalidPetAgeException, SQLException {
        if (pet.getAge() <= 0) {
            throw new InvalidPetAgeException("Pet age must be a positive integer.");
        }
        petDao.addPet(pet); 
    }

  
    public void removePet(Pet pet) throws SQLException {
        petDao.removePet(pet);
    }

  
    public List<Pet> getAllPets() throws SQLException {
        return petDao.getAllPets();
    }
}
