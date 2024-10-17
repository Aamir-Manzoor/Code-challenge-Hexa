package com.hexaware.petpals.entity.model;

public abstract class Donation {
    protected String donorName;
    protected double amount;

    public Donation(String donorName, double amount) {
        this.donorName = donorName;
        this.amount = amount;
    }

    public abstract void recordDonation();

	public abstract double getAmount();
}