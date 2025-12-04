package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    //Creates a list of all vehicles to reference back on
    ArrayList<Vehicle> inventory = new ArrayList<>();

    //Creates dealership blueprint and sets attributes
    public Dealership (String name, String address, String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    //Creates a list for each specific info to refer to
    public List<Vehicle> getVehicleByPrice(double min, double max){
        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : inventory){
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model){
        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : inventory){
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByYear(int min, int max){
        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : inventory){
            if (vehicle.getYear() >= min && vehicle.getYear() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByColor(String color){
        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : inventory){
            if (vehicle.getColor().equalsIgnoreCase(color)){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByMileage(int max, int min){
        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : inventory){
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByType(String type){
        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : inventory){
            if (vehicle.getVehicleType().equalsIgnoreCase(type)){
                result.add(vehicle);
            }
        }
        return result;
    }

    //Creates way to call every vehicle no matter the differences
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    //Creates way to add a new vehicle and save to file
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }

    //Creates way to delete a vehicle from file
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }
}