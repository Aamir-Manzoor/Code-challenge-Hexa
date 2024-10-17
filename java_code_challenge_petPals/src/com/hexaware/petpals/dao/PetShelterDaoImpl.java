package com.hexaware.petpals.dao;

import com.hexaware.petpals.exception.InvalidPetAgeException;
import com.hexaware.petpals.util.DBConnUtil;

import java.sql.*;
import java.util.Scanner;

public class PetShelterDaoImpl implements IPetShelterDao {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void displayPetListings() throws Exception {
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pets")) {

            System.out.println("=== Pet Listings ===");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") + ", Age: " + rs.getInt("age") + ", Breed: " + rs.getString("breed"));
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to fetch pet listings. " + e.getMessage());
        }
    }

    @Override
    public void addPet() throws Exception {
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO pets(petID, name, age, breed, type, AvailableForAdoption, SID) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            System.out.println("id:");
            int petid = sc.nextInt();
            System.out.println("name:");
            String name = sc.next();
            System.out.println("age:");
            int age = sc.nextInt();

            if (age <= 0) {
                throw new InvalidPetAgeException("InvalidPetAgeException: Pet age must be greater than 0.");
            }

            System.out.println("breed:");
            String breed = sc.next();
            System.out.println("type:");
            String type = sc.next();
            System.out.println("available for adoption (1 for yes, 0 for no):");
            int availableForAdoption = sc.nextInt();
            System.out.println("sid:");
            int sid = sc.nextInt();

            pstmt.setInt(1, petid);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, breed);
            pstmt.setString(5, type);
            pstmt.setInt(6, availableForAdoption);
            pstmt.setInt(7, sid);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new pet has been added.");
            } else {
                System.out.println("Failed to add the pet.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to insert pet. " + e.getMessage());
        }
    }

    @Override
    public void removePet() throws Exception {
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM pets WHERE PetID=?")) {

            System.out.println("id:");
            int petid = sc.nextInt();

            pstmt.setInt(1, petid);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("The pet has been removed.");
            } else {
                System.out.println("Failed to remove the pet.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to remove pet. " + e.getMessage());
        }
    }

    @Override
    public void listAvailablePets() throws Exception {
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pets WHERE AvailableForAdoption=1")) {

            System.out.println("=== Available Pets ===");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") + ", Age: " + rs.getInt("age") + ", Breed: " + rs.getString("breed"));
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to fetch available pets. " + e.getMessage());
        }
    }
}
