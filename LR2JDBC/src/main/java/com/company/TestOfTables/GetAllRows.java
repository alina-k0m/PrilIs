package com.company.TestOfTables;
import com.company.Connection.JDBC;

import java.sql.*;
import java.util.*;

public class GetAllRows {

    public static void main(String[] args) {
        Statement stmt = null;
        try {

            JDBC.connect();

            stmt = JDBC.connection.createStatement();

            String exampleQuery1 = "SELECT * FROM Authors";
            System.out.println("Authors:");
            ResultSet rs1 = stmt.executeQuery(exampleQuery1);
            while (rs1.next()) {
                int id = rs1.getInt("authorID");
                String firstName = rs1.getString("firstName");
                String lastName = rs1.getString("lastName");
                System.out.println(id + "\t" + firstName + "\t" + lastName);
            }

            String exampleQuery2 = "SELECT * FROM Authorisbn";
            System.out.println("Authorisbn:");
            ResultSet rs2 = stmt.executeQuery(exampleQuery2);
            while (rs2.next()) {
                int id = rs2.getInt("authorID");
                String isbn = rs2.getString("isbn");
                System.out.println(id + "\t" + isbn);
            }

            String exampleQuery3 = "SELECT * FROM Publishers";
            System.out.println("Publishers:");
            ResultSet rs3 = stmt.executeQuery(exampleQuery3);
            while (rs3.next()) {
                int publisherID = rs3.getInt("publisherID");
                String publisherName = rs3.getString("publisherName");
                System.out.println(publisherID + "\t" + publisherName);
            }


////////////////////////////////////////////////////////
        }
        catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block, used to close resources
            JDBC.close();
        }
    }
}