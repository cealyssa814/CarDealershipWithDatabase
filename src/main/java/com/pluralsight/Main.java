package com.pluralsight;
import com.pluralsight.UserInterface;
import javax.sql.DataSource;

// This is my new entry point that wires in JDBC.
public class Main {
    public static void main(String[] args) {
        // 1. Build the shared DataSource.
        DataSource dataSource = DataSourceFactory.getDataSource();

        // 2. Create the VehicleDao that talks to the DB.
        VehicleDao vehicleDao = new VehicleDao(dataSource);

        // 3. Build UI with my DAO (and still using ConsoleHelper).
        UserInterface ui = new UserInterface(vehicleDao);

        // 4. Start interactive menu loop.
        ui.display();
    }
}