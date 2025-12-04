package com.pluralsight.Data;

import com.pluralsight.Models.Dealership;
import com.pluralsight.Models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDataManager {

    public Dealership getDealership() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        // Step 1: Get dealership info
        String dealershipQuery = "SELECT * FROM dealerships LIMIT 1";
        PreparedStatement prepared = connection.prepareStatement(dealershipQuery);
        ResultSet resultSet = prepared.executeQuery();

        String name = "";
        String address = "";
        String phone = "";

        if (resultSet.next()) {
            name = resultSet.getString("name");
            address = resultSet.getString("address");
            phone = resultSet.getString("phone");
        }

        Dealership dealership = new Dealership(name, address, phone);

        // Step 2: Get all vehicles
        String vehicleQuery = "SELECT * FROM vehicles";
        PreparedStatement prepared1 = connection.prepareStatement(vehicleQuery);
        ResultSet resultSet1 = prepared1.executeQuery();

        while (resultSet1.next()) {
            int vin = resultSet1.getInt("vin");
            int year = resultSet1.getInt("year");
            String make = resultSet1.getString("make");
            String model = resultSet1.getString("model");
            String vehicleType = resultSet1.getString("vehicle_type");
            String color = resultSet1.getString("color");
            int odometer = resultSet1.getInt("odometer");
            double price = resultSet1.getDouble("price");

            Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.addVehicle(v);
        }

        connection.close();
        return dealership;
    }

    public List<Vehicle> getVehicleByPrice(double min, double max) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        PreparedStatement prepared = connection.prepareStatement(query);
        prepared.setDouble(1, min);
        prepared.setDouble(2, max);
        ResultSet resultSet = prepared.executeQuery();

        while (resultSet.next()) {
            int vin = resultSet.getInt("vin");
            int year = resultSet.getInt("year");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            String vehicleType = resultSet.getString("vehicleType");
            String color = resultSet.getString("color");
            int odometer = resultSet.getInt("odometer");
            double price = resultSet.getDouble("price");

            Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            vehicles.add(v);
        }

        connection.close();
        return vehicles;
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        PreparedStatement prepared = connection.prepareStatement(query);
        prepared.setString(1, make);
        prepared.setString(2, model);
        ResultSet resultSet = prepared.executeQuery();

        while (resultSet.next()) {
            int vin = resultSet.getInt("vin");
            int year = resultSet.getInt("year");
            String vehicleMake = resultSet.getString("make");
            String vehicleModel = resultSet.getString("model");
            String vehicleType = resultSet.getString("vehicleType");
            String color = resultSet.getString("color");
            int odometer = resultSet.getInt("odometer");
            double price = resultSet.getDouble("price");

            Vehicle v = new Vehicle(vin, year, vehicleMake, vehicleModel, vehicleType, color, odometer, price);
            vehicles.add(v);
        }

        connection.close();
        return vehicles;
    }

    public List<Vehicle> getVehicleByYear(int min, int max) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        PreparedStatement prepared = connection.prepareStatement(query);
        prepared.setInt(1, min);
        prepared.setInt(2, max);
        ResultSet resultSet = prepared.executeQuery();

        while (resultSet.next()) {
            int vin = resultSet.getInt("vin");
            int year = resultSet.getInt("year");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            String vehicleType = resultSet.getString("vehicleType");
            String color = resultSet.getString("color");
            int odometer = resultSet.getInt("odometer");
            double price = resultSet.getDouble("price");

            Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            vehicles.add(v);
        }

        connection.close();
        return vehicles;
    }

    public List<Vehicle> getVehicleByColor(String color) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE color = ?";
        PreparedStatement prepared = connection.prepareStatement(query);
        prepared.setString(1, color);
        ResultSet resultSet = prepared.executeQuery();

        while (resultSet.next()) {
            int vin = resultSet.getInt("vin");
            int year = resultSet.getInt("year");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            String vehicleType = resultSet.getString("vehicleType");
            String colorResult = resultSet.getString("color");
            int odometer = resultSet.getInt("odometer");
            double price = resultSet.getDouble("price");

            Vehicle v = new Vehicle(vin, year, make, model, vehicleType, colorResult, odometer, price);
            vehicles.add(v);
        }

        connection.close();
        return vehicles;
    }

    public List<Vehicle> getVehicleByMileage(int min, int max) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        PreparedStatement prepared = connection.prepareStatement(query);
        prepared.setInt(1, min);
        prepared.setInt(2, max);
        ResultSet resultSet = prepared.executeQuery();

        while (resultSet.next()) {
            int vin = resultSet.getInt("vin");
            int year = resultSet.getInt("year");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            String vehicleType = resultSet.getString("vehicleType");
            String color = resultSet.getString("color");
            int odometer = resultSet.getInt("odometer");
            double price = resultSet.getDouble("price");

            Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            vehicles.add(v);
        }

        connection.close();
        return vehicles;
    }

    public List<Vehicle> getVehicleByType(String type) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE vehicleType = ?";
        PreparedStatement prepared = connection.prepareStatement(query);
        prepared.setString(1, type);
        ResultSet resultSet = prepared.executeQuery();

        while (resultSet.next()) {
            int vin = resultSet.getInt("vin");
            int year = resultSet.getInt("year");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            String vehicleType = resultSet.getString("vehicleType");
            String color = resultSet.getString("color");
            int odometer = resultSet.getInt("odometer");
            double price = resultSet.getDouble("price");

            Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            vehicles.add(v);
        }

        connection.close();
        return vehicles;
    }

    //SELECT * FROM vehicles WHERE price BETWEEN ? AND ?
    //SELECT * FROM vehicles WHERE make = ? AND model = ?
    //SELECT * FROM vehicles WHERE year BETWEEN ? AND ?
    //SELECT * FROM vehicles WHERE color = ?
    //SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?
    //SELECT * FROM vehicles WHERE vehicleType = ?
    //SELECT * FROM vehicles

    //try (
    //            Connection connection = DriverManager.getConnection(URL, username, password);
    //            Statement statement = connection.createStatement();
    //            ResultSet results1 = statement.executeQuery(query1);
    //                )
    //        {
    //            // Getting the information and turning it into an object
    //            while (results1.next()) {
    //                int productID = results1.getInt("ProductID");
    //                String productName = results1.getString("ProductName");
    //                double unitPrice = results1.getDouble("UnitPrice");
    //                int unitsInStock = results1.getInt("UnitsInStock");
    //
    //                System.out.printf("\n%-10d %-35s %-13.2f %-10d\n", productID, productName, unitPrice, unitsInStock);
    //            }

    public static void saveDealership(Dealership d) {
        // Your new code here - saves to database
        // instead of writing file
    }

}