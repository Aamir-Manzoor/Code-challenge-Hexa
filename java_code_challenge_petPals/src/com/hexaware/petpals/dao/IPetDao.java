package com.hexaware.petpals.dao;

import com.hexaware.petpals.entity.model.*;

import java.sql.SQLException;
import java.util.List;

public interface IPetDao {
    void addPet(Pet pet) throws SQLException;
    void removePet(Pet pet) throws SQLException;
    List<Pet> getAllPets() throws SQLException;
}