package com.pluralsight.Data;

import com.pluralsight.Models.Contract;
import com.pluralsight.Models.LeaseContract;
import com.pluralsight.Models.SalesContract;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ContractFileManager {

    public void saveContract(Contract contract) {
        //Accept a Contract parameter
        // Use instanceof to determine contract type
        // Format output based on type:
        // For SalesContract: Include SALES_TAX, RECORDING_FEE, PROCESSING_FEE, FINANCE_OPTION, plus base fields
        // For LeaseContract: Include EXPECTED_ENDING_VALUE, LEASE_FEE, plus base fields
        // APPEND the contract data to the file (not overwrite)
        // Use pipe delimiters (|) to separate fields

        //APPENDING it to your contracts file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv", true))) {

            String line = "";

            //Lease
            if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;

                line = String.format("Lease|%s|%s|%s|%s|%.2f|%.2f",
                        lease.getContractDate(),
                        lease.getCustomerName(),
                        lease.getCustomerEmail(),
                        lease.getVehicleSold().getVin(),
                        lease.getTotalPrice(),
                        lease.getMonthlyPayment());

            }
            //Sales
            else if (contract instanceof SalesContract) {

                SalesContract sale = (SalesContract) contract;

                line = String.format("Sale|%s|%s|%s|%s|%.2f|%.2f",
                        sale.getContractDate(),
                        sale.getCustomerName(),
                        sale.getCustomerEmail(),
                        sale.getVehicleSold().getVin(),
                        sale.getTotalPrice(),
                        sale.getMonthlyPayment());

            }
            bw.write(line);
            bw.newLine();
            bw.close();

            System.out.println(line);

        } catch (IOException e) {
            System.out.println("There was an Error!");;
        }

    }

}
//    //Step 3: Test File Output
//    //Verify contracts are being appended correctly
//    //Compare format against the provided example data