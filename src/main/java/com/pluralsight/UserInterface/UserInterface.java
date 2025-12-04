package com.pluralsight.UserInterface;

import com.pluralsight.Data.ContractFileManager;
import com.pluralsight.Data.DealershipDataManager;
import com.pluralsight.Models.Dealership;
import com.pluralsight.Models.LeaseContract;
import com.pluralsight.Models.SalesContract;
import com.pluralsight.Models.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class UserInterface {

    public Dealership dealership;
    DealershipDataManager dealershipFileManager;

    public UserInterface() throws SQLException {
        DealershipDataManager dealFileManager = new DealershipDataManager();
        dealership = dealFileManager.getDealership();
    }

    public void display(){
        String mainMenu = """
                        
                        1 - Find vehicles within a price range
                        
                        2 - Find vehicles by make / model
                        
                        3 - Find vehicles by year range
                        
                        4 - Find vehicles by color
                        
                        5 - Find vehicles by mileage range
                        
                        6 - Find vehicles by type (car, truck, SUV, van)
                        
                        7 - List ALL vehicles
                        
                        8 - Add a vehicle
                        
                        9 - Remove a vehicle
                        
                        99 - Sell/Lease a vehicle
                        
                        0 - Quit
                        
                        """;

        while (true) {
            System.out.print(mainMenu);
            int command;

            command = ConsoleHelper.promptForInt("Enter here");

            switch (command) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    processSellOrLease();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid Entry!");
                    break;
            }
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("\nNo vehicles found.");
            return;
        }

        System.out.println("\n ⊰━━━━━━━━━━━━━━━━ Vehicles ━━━━━━━━━━━━━━━━⊱");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void processGetByPriceRequest(){
        System.out.println("What is the minimum and maximum price?");
        double minPrice = ConsoleHelper.promptForDouble("Enter minimum price");
        double maxPrice = ConsoleHelper.promptForDouble("Enter maximum price");

        List<Vehicle> result = dealership.getVehicleByPrice(minPrice, maxPrice);
        displayVehicles(result);
    }

    private void processGetByMakeModelRequest(){
        System.out.println("What is the Make and Model you are looking for?");
        String make = ConsoleHelper.promptForString("Enter Make");
        String model = ConsoleHelper.promptForString("Enter Model");

        List<Vehicle> result = dealership.getVehicleByMakeModel(make, model);
        displayVehicles(result);
    }

    private void processGetByYearRequest(){
        System.out.println("What is the year you are looking for?");
        int minYear = ConsoleHelper.promptForInt("Enter Minimum Year (YYYY)");
        int maxYear = ConsoleHelper.promptForInt("Enter Maximum Year (YYYY)");

        List<Vehicle> result = dealership.getVehicleByYear(minYear, maxYear);
        displayVehicles(result);
    }

    private void processGetByColorRequest(){
        System.out.println("What vehicle color you are looking for?");
        String color = ConsoleHelper.promptForString("Enter color");

        List<Vehicle> result = dealership.getVehicleByColor(color);
        displayVehicles(result);
    }

    private void processGetByMileageRequest(){
        System.out.println("What vehicle mileage you are looking for?");
        int minMileage = ConsoleHelper.promptForInt("Enter minimum mileage");
        int maxMileage = ConsoleHelper.promptForInt("Enter maximum mileage");

        // FIXED: Changed parameter order to match fixed Dealership method (min, max)
        List<Vehicle> result = dealership.getVehicleByMileage(minMileage, maxMileage);
        displayVehicles(result);
    }

    private void processGetByVehicleTypeRequest(){
        System.out.println("What Type of vehicle are you searching for?");
        String vehicleType = ConsoleHelper.promptForString("Enter vehicle type");

        List<Vehicle> result = dealership.getVehicleByType(vehicleType);
        displayVehicles(result);
    }

    private void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest(){
        int VIN = ConsoleHelper.promptForInt("What is the vehicle VIN number");
        int year = ConsoleHelper.promptForInt("What is the Year of your vehicle");
        String make = ConsoleHelper.promptForString("What is the vehicle make?");
        String model = ConsoleHelper.promptForString("What is the vehicle model?");
        String vehicleType = ConsoleHelper.promptForString("What is the vehicle type");
        String color = ConsoleHelper.promptForString("What is the color of the vehicle");
        int odometer = ConsoleHelper.promptForInt("What is the mileage of the vehicle");
        double price = ConsoleHelper.promptForDouble("What is your asking price for the vehicle");

        Vehicle vehicleToAdd = new Vehicle(VIN, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicleToAdd);

        DealershipDataManager.saveDealership(dealership);
    }

    private void processRemoveVehicleRequest(){
        int vin = ConsoleHelper.promptForInt("What is the vehicle VIN number");

        Vehicle found = null;

        for(Vehicle v : dealership.getAllVehicles()){
            if(vin == v.getVin()){
                found = v;
                break;
            }
        }

        if (found != null) {
            dealership.getAllVehicles().remove(found);
            DealershipDataManager.saveDealership(dealership);
            System.out.println("\nVehicle removed successfully!");
        }
        else {
            System.out.println("\nCould not find that Vehicles VIN");
        }
    }

    private void processSellOrLease(){
        String sellOrLease = """
                        S - Sell your vehicle
                        L - Lease your vehicle
                        B - Back to Main menu
                        """;
        while (true){
            System.out.println(sellOrLease);
            char command;

            command = ConsoleHelper.promptForChar("Enter your choice");

            switch (command){
                case 'S':
                    processSell();
                    break;
                case 'L':
                    processLease();
                    break;
                case 'B':
                    return;
                default:
                    System.out.println("\nInvalid Entry!");
                    break;
            }
        }
    }

    private void processSell(){
        int vin = ConsoleHelper.promptForInt("What is the vehicle VIN number");

        Vehicle vehicle = findVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("\nVehicle not found");
            return;
        }

        String customerName = ConsoleHelper.promptForString("Customer name");
        String customerEmail = ConsoleHelper.promptForString("Customer email");
        String date = ConsoleHelper.promptForString("Contract date (MM/DD/YYYY)");

        double salesTax = vehicle.getPrice() * 0.05;
        double recordingFee = 100.00;
        double processingFee = (vehicle.getPrice() < 10000) ? 295 : 495;
        char financeChar = ConsoleHelper.promptForChar("Finance this vehicle? (Y/N)");
        boolean finance = (financeChar == 'Y');

        SalesContract contract = new SalesContract(vehicle, customerEmail, customerName, date,
                salesTax, recordingFee, processingFee, finance);

        ContractFileManager contractManager = new ContractFileManager();
        contractManager.saveContract(contract);

        System.out.println("\n--- SALE CONTRACT SUMMARY ---");
        System.out.println("Total Price: $" + String.format("%.2f", contract.getTotalPrice()));
        if (finance) {
            System.out.println("Monthly Payment: $" + String.format("%.2f", contract.getMonthlyPayment()));
        } else {
            System.out.println("\nNo financing selected");
        }
        System.out.println("\nContract saved!");
    }

    private void processLease(){
        int vin = ConsoleHelper.promptForInt("What is the vehicle VIN number");

        Vehicle vehicle = findVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("\nVehicle not found");
            return;
        }

        // Check if vehicle is over 3 years old (can't lease)
        int currentYear = java.time.Year.now().getValue();
        if (currentYear - vehicle.getYear() > 3) {
            System.out.println("Cannot lease vehicles over 3 years old. This vehicle is " +
                    (currentYear - vehicle.getYear()) + " years old.");
            return;
        }

        String customerName = ConsoleHelper.promptForString("Customer name");
        String customerEmail = ConsoleHelper.promptForString("Customer email");
        String date = ConsoleHelper.promptForString("Contract date (MM/DD/YYYY)");

        double expectedValue = vehicle.getPrice() * 0.5;
        double leaseFee = vehicle.getPrice() * 0.07;

        LeaseContract contract = new LeaseContract(vehicle, customerEmail, customerName, date,
                expectedValue, leaseFee);

        ContractFileManager contractManager = new ContractFileManager();
        contractManager.saveContract(contract);

        System.out.println("\n--- LEASE CONTRACT SUMMARY ---");
        System.out.println("Total Price: $" + String.format("%.2f", contract.getTotalPrice()));
        System.out.println("Monthly Payment: $" + String.format("%.2f", contract.getMonthlyPayment()));
        System.out.println("\nContract saved!");
    }

    private Vehicle findVehicleByVin(int vin) {
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                return v;
            }
        }
        return null;
    }
}