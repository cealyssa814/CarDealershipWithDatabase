package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

// This class centralizes my DB connection settings.
// Anywhere in my app I can call DataSourceFactory.getDataSource().
public class DataSourceFactory {

    public static DataSource getDataSource() {
        // I’m using BasicDataSource so I get connection pooling for free.
        BasicDataSource dataSource = new BasicDataSource();

        // URL points to my CarDealership DB from Workshop 7.
        dataSource.setUrl("jdbc:mysql://localhost:3306/CarDealership");
        // I'm using my local MySQL username/password here.
        dataSource.setUsername("root");
        dataSource.setPassword("password123");

        // Optional tuning so it doesn’t open a million connections.
        dataSource.setMinIdle(1);
        dataSource.setMaxIdle(5);
        dataSource.setMaxTotal(10);

        return dataSource;
    }
    DataSource ds = DataSourceFactory.getDataSource();

}