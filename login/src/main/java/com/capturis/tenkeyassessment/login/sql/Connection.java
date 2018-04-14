package com.capturis.tenkeyassessment.login.sql;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
private static  final String DB_CONN = "jdbc:postgresql://localhost:5432/thedb";
  private static  final String DB_USER = "username";
  private static  final String DB_PW = "password";

  public Statement setupStatement() throws SQLException, IOException {

    java.sql.Connection connection = setUpCommonConnection();
    return connection.createStatement();
  }

  private java.sql.Connection setUpCommonConnection() throws SQLException, IOException {

    return DriverManager.getConnection(DB_CONN, DB_USER, DB_PW);
  }
}
