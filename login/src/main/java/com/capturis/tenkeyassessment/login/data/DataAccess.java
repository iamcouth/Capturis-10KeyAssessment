package com.capturis.tenkeyassessment.login.data;

import com.capturis.tenkeyassessment.login.model.UserLogin;
import com.capturis.tenkeyassessment.login.sql.Connection;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class DataAccess extends Connection
{

    private final Statement statement;

    @Inject
    public DataAccess() throws SQLException, IOException {
      this.statement = setupStatement();
    }

//  public void AddUser(UserLogin params) {
//    Connection conn = null;
//    try {
//      conn = getDBConn();
//      PreparedStatement ps = conn.prepareStatement("INSERT INTO testhash(plainpass, hashpass, matc) VALUES (?, ?, ?, ?)");
//
//      ps.setString(1, params.getPlainText());
//      ps.setString(2, params.getHashed());
//      ps.setBoolean(3, params.isMatched());
//      ps.setInt(4, params.getId());
//
//      ps.executeUpdate();
//    }
//    catch (SQLException e)
//    {
//      String.format(e.getMessage());
//    }
//  }

  public UserLogin getById(int id) throws SQLException{
    String sql = "SELECT * FROM userlogin where userid = " + id;
    ResultSet rs = statement.executeQuery(sql);
    if (rs.next()) {
      return userLoginMap(rs);
    }
    else
    {
      return null;
    }
  }

  private UserLogin userLoginMap(ResultSet rs) throws SQLException {
    int userloginid = rs.getInt("userloginid");
    String username = rs.getString("username");
    String passwordhash = rs.getString("passwordhash");
    int userid = rs.getInt("userid");
    boolean accountlock_fl = rs.getBoolean("accountlock_fl");
    Timestamp lastlogindate = rs.getTimestamp("lastlogindate");

    UserLogin userLogin = new UserLogin();
    userLogin.setUserLoginId(userloginid);
    userLogin.setUsername(username);
    userLogin.setPasswordHash(passwordhash);
    userLogin.setUserId(userid);
    userLogin.setAccountLockFl(accountlock_fl);
    userLogin.setLastLoginDate(lastlogindate);

    return userLogin;

  }

}
