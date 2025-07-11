/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mystrive.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mystrive_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USERNAME = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = ""; // Replace with your MySQL password

    // JDBC Driver Name
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());


    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            LOGGER.log(Level.INFO, "Connecting to database...");
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            LOGGER.log(Level.INFO, "Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found. Make sure the JAR is in WEB-INF/lib.", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to connect to database: " + e.getMessage(), e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.INFO, "Database connection closed.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing database connection: " + e.getMessage(), e);
            }
        }
    }
}
