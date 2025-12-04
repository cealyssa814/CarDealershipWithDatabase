package com.pluralsight.Models;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financeOption;


    public SalesContract(Vehicle vehicleSold, String customerEmail, String customerName, String date,
                         double salesTaxAmount, double recordingFee, double processingFee, boolean financeOption) {
        super(vehicleSold, customerEmail, customerName, date);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.financeOption = financeOption;
    }

    // this.salesTax = getVehicleSold().getPrice() * 0.05;
    // sales tax is 5% of the Vehicles Price
    // this.recordingFee = 100.00;
    // Recording Fee ($100)
    // this.processingFee = (vehicleSold.getPrice() < 10_000) ? 295 : 495 ;
    // Processing fee ($295 for vehicles under $10,000 and $495 for all others
    // this.finance = finance;
    // Monthly payment (if financed)

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinanceOption() {
        return financeOption;
    }


    @Override
    public double getTotalPrice() {
        // Calculate as vehicle price + sales tax (5%) +
        // recording fee ($100) + processing fee ($295 or $495)
        double vehiclePrice = getVehicleSold().getPrice();

        // total + interest + recording fee + processing fee
        double total = vehiclePrice + (vehiclePrice * 0.05) + recordingFee + processingFee;

        return total;
    }

    @Override
    public double getMonthlyPayment() {
        if (!financeOption) {
            return 0.00;
        }

        double totalPrice = getTotalPrice();
        double vehiclePrice = getVehicleSold().getPrice();

        double annualRate = (vehiclePrice < 10000) ? 0.0525 : 0.0425;
        int months = (vehiclePrice < 10000) ? 24 : 48;

        double monthlyRate = annualRate / 12;
        double monthlyPayment = (totalPrice / months) + (totalPrice * monthlyRate);

        return monthlyPayment;
    }
}