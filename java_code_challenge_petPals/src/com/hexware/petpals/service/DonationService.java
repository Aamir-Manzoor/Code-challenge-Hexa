package com.hexware.petpals.service;

import com.hexaware.petpals.entity.model.Donation; 
import com.hexaware.petpals.exception.InsufficientFundsException; 

public class DonationService {
    private static final double MINIMUM_DONATION = 10.0;

    public void processDonation(Donation donation) throws InsufficientFundsException {
      
        if (donation.getAmount() < MINIMUM_DONATION) {
            throw new InsufficientFundsException("Donation amount must be at least $" + MINIMUM_DONATION);
        }
        
     
        donation.recordDonation(); 
    }
}
