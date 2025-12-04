import com.pluralsight.Dealership;
import com.pluralsight.Vehicle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

//Logic behind reading between "|" info in inventory file

public Dealership getDealership() {

    Dealership d = null;
    try {
        //Creates File/Buffered reader, splits between "|", reads first line separately from the rest
        FileReader fileReader = new FileReader("inventory.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String firstLine = bufferedReader.readLine();

        String[] line = firstLine.split("\\|");
        String name = line[0];
        String address = line[1];
        String phone = line[2];

        d = new Dealership(name, address, phone);
        String lineFromString;

        while ((lineFromString = bufferedReader.readLine()) != null) {

            String[] part = lineFromString.split("\\|");
            int vin = Integer.parseInt(part[0]);
            int year = Integer.parseInt(part[1]);
            String make = part[2];
            String model = part[3];
            String vehicleType = part[4];
            String color = part[5];
            int odometer = Integer.parseInt(part[6]);
            double price = Double.parseDouble(part[7]);

            //Creates vehicle object here
            Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

            d.addVehicle(v);
        }


    } catch (Exception e) {
        System.out.println("Error");
    }
    return d;
}

//Logic behind writing and saving info in file
public void saveDealership(Dealership d) {

    //Creates File/Buffered writer, Asks for specific info to split between "|"
    try {
        FileWriter fileWriter = new FileWriter("inventory.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Vehicle v : d.getAllVehicles()) {
            bufferedWriter.newLine();
            bufferedWriter.write(v.getVin() + " | "
                    + v.getYear() + " | "
                    + v.getMake() + " | "
                    + v.getModel() + " | "
                    + v.getVehicleType() + " | "
                    + v.getColor() + " | "
                    + v.getOdometer() + " | "
                    + v.getPrice());
        }
        bufferedWriter.close();

    } catch (Exception e) {
        System.out.println("Error");
    }

}

void main() {
}


