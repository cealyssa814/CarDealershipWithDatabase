package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    private final Dealership dealership;
    private final VehicleDao vehicleDao;

    public UserInterface(VehicleDao vehicleDao) {
        // I keep a simple Dealership object for metadata (name, address, phone).
        // Later, I can load those fields from the DB as well if I want.
        this.dealership = new Dealership("D & B Used Cars",
                "123 Main St",
                "555-1234");
        this.vehicleDao = vehicleDao;
    }
    public void display() {
        String mainMenu = "1 - Find vehicles within a price range\n" +
                "2 - Find vehicles by make / model\n" +
                "3 - Find vehicles by year range\n" +
                "4 - Find vehicles by color\n" +
                "5 - Find vehicles by mileage range\n" +
                "6 - Find vehicles by type (car, truck, SUV, van)\n" +
                "7 - List ALL vehicles\n" +
                "8 - Add a vehicle\n" +
                "9 - Remove a vehicle\n" +
                "0 - Quit \n";
        while (true) {
            System.out.print(mainMenu);
            int command = ConsoleHelper.promptForInt("Enter here");

            switch (command) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> {  //exit
                    return;
                }

            }
        }
    }

    private void processGetByPriceRequest() {
        System.out.println("What is the minimum and maximum price?");

        double minPrice = ConsoleHelper.promptForDouble("Enter minimum price");
        double maxPrice = ConsoleHelper.promptForDouble("Enter maximum price");

        // Here I’m calling the DAO instead of dealership.getVehicleByPrice
        List<Vehicle> vehiclesByPrice = vehicleDao.findByPriceRange(minPrice, maxPrice);

        displayVehicles(vehiclesByPrice);
    }

    private void displayVehicles(List<Vehicle> vehiclesByPrice) {
    }

    private void processGetByMakeModelRequest() {
        System.out.println("What is the Make and Model you are looking for?");
        String make = ConsoleHelper.promptForString("Enter Make");
        String model = ConsoleHelper.promptForString("Enter Model");

        List<Vehicle> vehicles = vehicleDao.findByMakeModel(make, model);
        displayVehicles(vehicles);
    }


    private void processGetByYearRequest() {
        System.out.println("What is the year you are looking for?");
        int minYear = ConsoleHelper.promptForInt("Enter Minimum Year (YYYY)");
        int maxYear = ConsoleHelper.promptForInt("Enter Maximum Year (YYYY)");

        List<Vehicle> vehiclesByYear = vehicleDao.findByYearRange(minYear, maxYear);
        System.out.println(vehiclesByYear);
    }

    private void processGetByColorRequest() {
        System.out.println("What vehicle color you are looking for?");
        String color = ConsoleHelper.promptForString("Enter color");

        List<Vehicle> vehiclesByColor = vehicleDao.findByColor(color);
        System.out.println(vehiclesByColor);
    }

    private void processGetByMileageRequest() {
        System.out.println("What vehicle mileage you are looking for?");
        int minMileage = ConsoleHelper.promptForInt("Enter minimum mileage");
        int maxMileage = ConsoleHelper.promptForInt("Enter maximum mileage");

        List<Vehicle> vehiclesByMileage = vehicleDao.findByMileageRange(min, max);
        System.out.println(vehiclesByMileage);
    }

    private void processGetByVehicleTypeRequest() {
        System.out.println("What Type of vehicle are you searching for?");
        String vehicleType = ConsoleHelper.promptForString("Enter vehicle type");

        List<Vehicle> vehiclesByType = vehicleDao.findByType(type);
        System.out.println(vehiclesByType);
    }

    private void processGetAllVehiclesRequest() {
        List<Vehicle> vehiclesByType =vehicleDao.findAll();
        System.out.println(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest(){
        int vin  = ConsoleHelper.promptForInt("What is the vehicle VIN number");
        int year = ConsoleHelper.promptForInt("What is the Year of your vehicle");
        String make = ConsoleHelper.promptForString("What is the vehicle make?");
        String model = ConsoleHelper.promptForString("What is the vehicle model?");
        String vehicleType = ConsoleHelper.promptForString("What is the vehicle type");
        String color = ConsoleHelper.promptForString("What is the color of the vehicle");
        int odometer = ConsoleHelper.promptForInt("What is the mileage of the vehicle");
        double price = ConsoleHelper.promptForDouble("What is your asking price for the vehicle");

        Vehicle vehicleToAdd = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);

        // Instead of adding to dealership + saving CSV,
        // I now add directly to the database.
        vehicleDao.add(vehicleToAdd);

        System.out.println("Vehicle added to database.");
    }


    private void processRemoveVehicleRequest(){
        int vin  = ConsoleHelper.promptForInt("What is the vehicle VIN number");

        boolean success = vehicleDao.deleteByVin(vin);

        if (success) {
            System.out.println("Vehicle removed from database!");
        } else {
            System.out.println("Could not find that vehicle VIN in the database.");
        }
    }
}
