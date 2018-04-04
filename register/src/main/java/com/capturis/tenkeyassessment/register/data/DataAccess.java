package com.capturis.tenkeyassessment.register.data;

import com.capturis.tenkeyassessment.register.models.AssessmentUser;

import java.sql.*;
import java.time.Instant;

public class DataAccess {

    private static  final String DB_CONN = "jdbc:postgresql://localhost:5432/thedatabase";
    private static  final String DB_USER = "username";
    private static  final String DB_PW = "password";

    private  static Connection getDBConn()
    {
        Connection dbConn = null;
        try
        {
            dbConn = DriverManager.getConnection(DB_CONN, DB_USER, DB_PW);
            return dbConn;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return  dbConn;
    }

    public void AddUser(AssessmentUser params)
    {
        Connection conn = null;
        try {
            conn = getDBConn();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO AssessmentUser (firstname, lastname, emailaddress, phonenumber, roleid, createddate, street, city, state, zipcode, country, jobcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, params.getFirstName());
            ps.setString(2, params.getLastName());
            ps.setString(3, params.getEmailAddress());
            ps.setString(4, params.getPhoneNumber());
            ps.setInt(5, 1);
            ps.setTimestamp(6, Timestamp.from(Instant.now()));
            ps.setString(7, params.getStreet());
            ps.setString(8, params.getCity());
            ps.setString(9, params.getState());
            ps.setString(10, params.getZipCode());
            ps.setString(11, params.getCountry());
            ps.setString(12, params.getJobCode());

            ps.executeUpdate();
        }

        catch (SQLException e)
        {
            String.format(e.getMessage());
        }
    }
}
