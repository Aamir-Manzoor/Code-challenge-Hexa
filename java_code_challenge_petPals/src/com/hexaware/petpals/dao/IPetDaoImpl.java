package com.hexaware.petpals.dao;

import com.hexaware.petpals.entity.model.Pet;
import com.hexaware.petpals.util.DBConnUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IPetDaoImpl implements IPetDao {
    private Connection connection;

    public IPetDaoImpl() throws SQLException, IOException {
        this.connection = DBConnUtil.getConnection();
    }

    @Override
    public void addPet(Pet pet) throws SQLException {
        String sql = "INSERT INTO pets (name, age, breed) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setInt(2, pet.getAge());
            pstmt.setString(3, pet.getBreed());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void removePet(Pet pet) throws SQLException {
        String sql = "DELETE FROM pets WHERE name = ? AND age = ? AND breed = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setInt(2, pet.getAge());
            pstmt.setString(3, pet.getBreed());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Pet> getAllPets() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pet pet = new Pet(rs.getString("name"), rs.getInt("age"), rs.getString("breed"));
                pets.add(pet);
            }
        }
        return pets;
    }
}
