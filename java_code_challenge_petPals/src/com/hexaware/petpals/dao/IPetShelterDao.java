package com.hexaware.petpals.dao;

import com.hexaware.petpals.exception.*;

public interface IPetShelterDao {
    void displayPetListings() throws Exception;

    void addPet() throws InvalidPetAgeException, Exception;

    void removePet() throws Exception;

    void listAvailablePets() throws Exception;
}
