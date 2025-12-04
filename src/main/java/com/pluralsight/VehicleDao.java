package com.pluralsight;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// This DAO knows how to talk to the vehicles table.
// It replaces the CSV-backed searches in my advanced Dealership.
public class VehicleDao {

    private final DataSource dataSource;

    // I inject the DataSource instead of hardcoding connections.
    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Helper: convert one DB row into a Vehicle object.
    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        // I'm assuming my DB columns are named like this; I match them to my Vehicle fields.
        int vin = rs.getInt("vin");
        int year = rs.getInt("year");
        String make = rs.getString("make");
        String model = rs.getString("model");
        String type = rs.getString("vehicle_type");
        String color = rs.getString("color");
        int odometer = rs.getInt("odometer");
        double price = rs.getDouble("price");

        return new Vehicle(vin, year, make, model, type, color, odometer, price);
    }

    // This replaces my old getVehicleByPrice — but now it hits the database.
    public List<Vehicle> findByPriceRange(double min, double max) {
        List<Vehicle> results = new ArrayList<>();

        String sql = """
                SELECT vin, year, make, model, vehicle_type, color, odometer, price
                FROM vehicles
                WHERE price BETWEEN ? AND ?
                ORDER BY price
                """;

        // I use try-with-resources like the JDBC workbook shows,
        // so Connection/PreparedStatement/ResultSet close automatically.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, min);
            stmt.setDouble(2, max);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRowToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public List<Vehicle> findByMakeModel(String make, String model) {
        List<Vehicle> results = new ArrayList<>();

        String sql = """
                SELECT vin, year, make, model, vehicle_type, color, odometer, price
                FROM vehicles
                WHERE make LIKE ? AND model LIKE ?
                ORDER BY year DESC
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // I wrap with % so users can search partial make/model.
            stmt.setString(1, "%" + make + "%");
            stmt.setString(2, "%" + model + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRowToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public List<Vehicle> findByYearRange(int minYear, int maxYear) {
        List<Vehicle> results = new ArrayList<>();

        String sql = """
                SELECT vin, year, make, model, vehicle_type, color, odometer, price
                FROM vehicles
                WHERE year BETWEEN ? AND ?
                ORDER BY year DESC
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, minYear);
            stmt.setInt(2, maxYear);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRowToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
