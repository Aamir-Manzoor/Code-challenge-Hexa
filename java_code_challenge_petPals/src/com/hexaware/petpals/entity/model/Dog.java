package com.hexaware.petpals.entity.model;

public class Dog extends Pet {
    private String dogBreed;

    public Dog(String name, int age, String breed, String dogBreed) {
        super(name, age, breed);
        this.dogBreed = dogBreed;
    }

    public String getDogBreed() { return dogBreed; }
    public void setDogBreed(String dogBreed) { this.dogBreed = dogBreed; }

    @Override
    public String toString() {
        return "Dog{" + super.toString() + ", dogBreed='" + dogBreed + "'}";
    }
}
