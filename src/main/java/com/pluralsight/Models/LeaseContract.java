package com.pluralsight.Models;

public class LeaseContract extends Contract {
    private double expectedValue;
    private double leaseFee;

    public LeaseContract(Vehicle vehicleSold, String customerEmail, String customerName,
                         String date, double expectedValue, double leaseFee) {
        super(vehicleSold, customerEmail, customerName, date);

        // Calculate the lease-specific values:
        this.expectedValue = vehicleSold.getPrice() * 0.5; // Expected Ending Value  (50% of the original price)
        this.leaseFee = vehicleSold.getPrice() * 0.07;     // Lease Fee (7% of the original price)

    }

    // Methods will include a constructor and getters and setters for all fields except
    // total price and monthly payment.  You should provide overrides for
    // getTotalPrice() and getMonthlyPayment() that will return computed

    public double getExpectedEndingValue() {
        return expectedValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        //Calculate as vehicle price + lease fee (7% of price)
        return getVehicleSold().getPrice() + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        // Monthly payment based on
        // All leases are financed at 4.0% for 36 months
        double totalPrice = getTotalPrice();

        double annualRate = 0.04;
        double monthlyRate = annualRate / 12;
        int months = 36;

        double monthlyPayment = (totalPrice / months) + (totalPrice * monthlyRate);
        return monthlyPayment;
    }

}