package com.hexaware.petpals.entity.model;

public class ItemDonation extends Donation {
    private String itemType;

    public ItemDonation(String donorName, double amount, String itemType) {
        super(donorName, amount);
        this.itemType = itemType;
    }

    @Override
    public void recordDonation() {
        // Implement the logic to record the item donation
        System.out.println("Recorded item donation of " + itemType + " valued at $" + getAmount() + " from " + getDonorName());
    }

    private String getDonorName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public double getAmount() {
		return amount;
    }

    public String getItemType() {
        return itemType;
    }
}
