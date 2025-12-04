package com.pluralsight.Models;

public abstract class Contract {
    private String date; //(as string) of contract
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
//    These will be calculated
//    int totalPrice;
//    int monthlyPayment;

    public Contract(Vehicle vehicleSold, String customerEmail, String customerName, String date) {
        this.vehicleSold = vehicleSold;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.date = date;
    }

    public String getContractDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }
    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    // will return computed values based on contract type
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}