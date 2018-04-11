package com.capturis.tenkeyassessment.login.data;

import com.capturis.tenkeyassessment.login.model.UserLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataAccess
{


  private  static Connection getDBConn() {
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
  public void AddUser(UserLogin params) {
    Connection conn = null;
    try {
      conn = getDBConn();
      PreparedStatement ps = conn.prepareStatement("INSERT INTO testhash(plainpass, hashpass, matc) VALUES (?, ?, ?)");

      ps.setString(1, params.getPlainText());
      ps.setString(2, params.getHashed());
      ps.setBoolean(3, params.isMatched());

      ps.executeUpdate();
    }
    catch (SQLException e)
    {
      String.format(e.getMessage());
    }
  }

}
