package com.hexaware.petpals.presentation.main;

import com.hexaware.petpals.dao.IPetDao;
import com.hexaware.petpals.dao.IPetDaoImpl;
import com.hexaware.petpals.entity.model.CashDonation;
import com.hexaware.petpals.entity.model.Donation;
import com.hexaware.petpals.entity.model.ItemDonation;
import com.hexaware.petpals.entity.model.Pet;
import com.hexaware.petpals.entity.model.PetShelter;
import com.hexaware.petpals.exception.AdoptionException;
import com.hexaware.petpals.exception.InvalidPetAgeException;
import com.hexaware.petpals.exception.InsufficientFundsException;

import com.hexaware.petpals.util.DBConnUtil;
import com.hexaware.petpals.util.DBPropertyUtil;
import com.hexware.petpals.service.AdoptionService;
import com.hexware.petpals.service.DonationService;
import com.hexware.petpals.service.PetService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    private static Scanner scanner = new Scanner(System.in);
    private static PetService petService;
    private static DonationService donationService;
    private static AdoptionService adoptionService;

    public static void main(String[] args) {
        try {
            String connectionString = DBPropertyUtil.getConnectionString();
            System.out.println("Connection String: " + connectionString);
            
            try (Connection conn = DBConnUtil.getConnection()) {
                if (conn != null) {
                    System.out.println("Database connected successfully!");
                }

                IPetDao petDAO = new IPetDaoImpl();
                petService = new PetService(petDAO);
                donationService = new DonationService();
                PetShelter shelter = new PetShelter();
                adoptionService = new AdoptionService(shelter);
                menuLoop();
            }
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
            scanner.close(); 
        }
    }

    private static void menuLoop() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPet();
                    break;
                case 2:
                    listPets();
                    break;
                case 3:
                    makeDonation();
                    break;
                case 4:
                    adoptPet();
                    break;
                case 5:
                    System.out.println("Thank you.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- PetPals Menu ---");
        System.out.println("1. Add a pet");
        System.out.println("2. List available pets");
        System.out.println("3. Make a donation");
        System.out.println("4. Adopt a pet");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addPet() {
        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();
        System.out.print("Enter pet age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear the newline
        System.out.print("Enter pet breed: ");
        String breed = scanner.nextLine();

        Pet pet = new Pet(name, age, breed);
        try {
            petService.addPet(pet);
            System.out.println("Pet added successfully!");
        } catch (InvalidPetAgeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void listPets() {
        try {
            List<Pet> pets = petService.getAllPets();
            if (pets.isEmpty()) {
                System.out.println("No pets available.");
            } else {
                System.out.println("Available pets:");
                for (Pet pet : pets) {
                    System.out.println(pet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void makeDonation() {
        System.out.print("Enter donor name: ");
        String donorName = scanner.nextLine();
        System.out.print("Enter donation amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear the newline

        System.out.print("Is this a cash donation? (yes/no): ");
        String donationType = scanner.nextLine().toLowerCase();

        Donation donation;
        if (donationType.equals("yes")) {
            donation = new CashDonation(donorName, amount, LocalDate.now());
        } else {
            System.out.print("Enter item type: ");
            String itemType = scanner.nextLine();
            donation = new ItemDonation(donorName, amount, itemType);
        }

        try {
            donationService.processDonation(donation);
            System.out.println("Thank you for your donation!");
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void adoptPet() {
        try {
            List<Pet> pets = petService.getAllPets();
            if (pets.isEmpty()) {
                System.out.println("No pets available for adoption.");
                return;
            }

            System.out.println("Available pets for adoption:");
            for (int i = 0; i < pets.size(); i++) {
                System.out.println((i + 1) + ". " + pets.get(i));
            }

            System.out.print("Enter the number of the pet you want to adopt: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline

            if (choice < 1 || choice > pets.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            Pet selectedPet = pets.get(choice - 1);
            adoptionService.adoptPet(selectedPet);
            System.out.println("You have successfully adopted " + selectedPet.getName() + "!");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (AdoptionException e) {
            System.out.println("Adoption error: " + e.getMessage());
        }
    }
}
