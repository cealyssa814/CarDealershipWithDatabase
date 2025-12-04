package com.pluralsight;

import com.pluralsight.UserInterface.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        //try{
        //Basicds ds = getConnection(args);

        //DealrshipDataManager dm = new DealershipDataManager(ds);



        UserInterface ui = new UserInterface();
        ui.display();
    }
}