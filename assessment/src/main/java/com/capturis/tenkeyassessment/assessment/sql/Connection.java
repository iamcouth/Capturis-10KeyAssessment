package com.capturis.tenkeyassessment.assessment.sql;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
  private static  final String DB_CONN = "jdbc:postgresql://134.129.125.210:5432/capturis_10keyassessment";
  private static  final String DB_USER = "tester";
  private static  final String DB_PW = "test1234";

  public Statement setupStatement() throws SQLException, IOException {

    java.sql.Connection connection = setUpCommonConnection();
    return connection.createStatement();
  }

  public PreparedStatement setupPreparedStatement(String sql) throws SQLException, IOException {
    java.sql.Connection connection = setUpCommonConnection();
    return connection.prepareStatement(sql);
  }

  private java.sql.Connection setUpCommonConnection() throws SQLException, IOException {

    return DriverManager.getConnection(DB_CONN, DB_USER, DB_PW);
  }
}
