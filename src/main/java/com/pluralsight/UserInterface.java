package com.pluralsight;

import java.util.ArrayList;

public class UserInterface {

    private Dealership dealership;
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
        String mainMenu =
                "1 - Find vehicles within a price range\n" +
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
            int command;

            command = ConsoleHelper.promptForInt("Enter here"); //prompt for main menu

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
                case 0: //exit
                    return;
                default:
                    System.out.println("Invalid entry"); //Error message
                    break;

            }
        }
    }

    private void processGetByPriceRequest() {
        System.out.println("What is the minimum and maximum price?");
        double minPrice = ConsoleHelper.promptForDouble("Enter minimum price");
        double maxPrice = ConsoleHelper.promptForDouble("Enter maximum price");

        ArrayList<Vehicle> vehiclesByPrice = (ArrayList<Vehicle>) dealership.getVehicleByPrice(minPrice, maxPrice);

        System.out.println(vehiclesByPrice);
    }

    private void processGetByMakeModelRequest() {
        System.out.println("What is the Make and Model you are looking for?");
        String make = ConsoleHelper.promptForString("Enter Make");
        String model = ConsoleHelper.promptForString("Enter Model");

        ArrayList<Vehicle> vehiclesByMakeModel = (ArrayList<Vehicle>) dealership.getVehicleByMakeModel(make, model);

        System.out.println(vehiclesByMakeModel);
    }

    private void processGetByYearRequest() {
        System.out.println("What is the year you are looking for?");
        int minYear = ConsoleHelper.promptForInt("Enter Minimum Year (YYYY)");
        int maxYear = ConsoleHelper.promptForInt("Enter Maximum Year (YYYY)");

        ArrayList<Vehicle> vehiclesByYear = (ArrayList<Vehicle>) dealership.getVehicleByYear(minYear, maxYear);
        System.out.println(vehiclesByYear);
    }

    private void processGetByColorRequest() {
        System.out.println("What vehicle color you are looking for?");
        String color = ConsoleHelper.promptForString("Enter color");

        ArrayList<Vehicle> vehiclesByColor = (ArrayList<Vehicle>) dealership.getVehicleByColor(color);
        System.out.println(vehiclesByColor);
    }

    private void processGetByMileageRequest() {
        System.out.println("What vehicle mileage you are looking for?");
        int minMileage = ConsoleHelper.promptForInt("Enter minimum mileage");
        int maxMileage = ConsoleHelper.promptForInt("Enter maximum mileage");

        ArrayList<Vehicle> vehiclesByMileage = (ArrayList<Vehicle>) dealership.getVehicleByMileage(minMileage, maxMileage);
        System.out.println(vehiclesByMileage);
    }

    private void processGetByVehicleTypeRequest() {
        System.out.println("What Type of vehicle are you searching for?");
        String vehicleType = ConsoleHelper.promptForString("Enter vehicle type");

        ArrayList<Vehicle> vehiclesByType = (ArrayList<Vehicle>) dealership.getVehicleByType(vehicleType);
        System.out.println(vehiclesByType);
    }

    private void processGetAllVehiclesRequest() {
        System.out.println(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest() {
        int VIN = ConsoleHelper.promptForInt("What is the vehicle VIN number");
        int year = ConsoleHelper.promptForInt("What is the Year of your vehicle");
        String make = ConsoleHelper.promptForString("What is the vehicle make?");
        String model = ConsoleHelper.promptForString("What is the vehicle model?");
        String vehicleType = ConsoleHelper.promptForString("What is the vehicle type");
        String color = ConsoleHelper.promptForString("What is the color of the vehicle");
        int odometer = ConsoleHelper.promptForInt("What is the mileage of the vehicle");
        double price = ConsoleHelper.promptForDouble("What is you asking price for the vehicle");

        Vehicle vehicleToAdd = new Vehicle(VIN, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicleToAdd);

    }

    private void processRemoveVehicleRequest() {
        int vin = ConsoleHelper.promptForInt("What is the vehicle VIN number");

        boolean found = false;

        for (Vehicle v : dealership.getAllVehicles()) {
            if (vin == v.getVin()) {
                found = true;
                dealership.removeVehicle(v);
                System.out.println("Vehicle Removed!");
                break;
            }
        }

        if (found == false) {
            System.out.println("Could not find that Vehicles VIN");
        }
    }
}