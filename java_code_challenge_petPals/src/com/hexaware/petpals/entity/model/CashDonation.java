package com.hexaware.petpals.entity.model;

import java.time.LocalDate;

public class CashDonation extends Donation {
    private LocalDate donationDate;

    public CashDonation(String donorName, double amount, LocalDate donationDate) {
        super(donorName, amount);
        this.donationDate = donationDate;
    }

    @Override
    public void recordDonation() {
        System.out.println("Recorded cash donation of $" + amount + " from " + donorName + " on " + donationDate);
    }

	@Override
	public double getAmount() {
		
		return amount;
	}
}