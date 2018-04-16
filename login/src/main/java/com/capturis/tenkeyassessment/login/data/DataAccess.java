package com.capturis.tenkeyassessment.login.data;

import com.capturis.tenkeyassessment.login.model.UserLogin;
import com.capturis.tenkeyassessment.login.sql.Connection;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess extends Connection
{

    private final Statement statement;
    private PreparedStatement ps;

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

  public List<UserLogin> findAll() throws SQLException{

      List<UserLogin> list = new ArrayList<UserLogin>();
      String sql = "SELECT * FROM userlogin ORDER BY userid";

      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        list.add(userLoginMap(rs));
      }

      return list;
  }

  public UserLogin save(UserLogin userLogin) throws SQLException, IOException {
      return userLogin.getUserId() > 0 ? update(userLogin) : create(userLogin);
  }

  public UserLogin create(UserLogin userLogin) throws SQLException, IOException {
    String sql = "INSERT INTO userlogin (username, userid, passwordhash, accountlock_fl, lastlogindate) VALUES (?, ?, ?, ?, ?)";
      ps = setupPreparedStatement(sql);

      ps.setString(1, userLogin.getUsername());
      ps.setInt(2, userLogin.getUserId());
      ps.setString(3, userLogin.getPasswordHash());
      ps.setBoolean(4, userLogin.isAccountLockFl());
      ps.setTimestamp(5, userLogin.getLastLoginDate());

      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();

      rs.next();

      int id = rs.getInt(1);
      userLogin.setUserLoginId(id);

      return userLogin;

  }

  public UserLogin update(UserLogin userLogin) throws SQLException, IOException {

    String sql = "UPDATE userlogin SET username = ?, passwordhash = ?, accountlock_fl = ? WHERE userid = ?";
      PreparedStatement ps = setupPreparedStatement(sql);

      ps.setString(1, userLogin.getUsername());
      ps.setString(2, userLogin.getPasswordHash());
      ps.setBoolean(3, userLogin.isAccountLockFl());
      ps.setInt(4, userLogin.getUserId());

      ps.executeUpdate();

      return userLogin;
  }

  public boolean remove(int id) throws SQLException, IOException {
      String sql = "DELETE FROM userlogin where userloginid = ?";
      PreparedStatement ps = setupPreparedStatement(sql);
      ps.setInt(1, id);

      int count = ps.executeUpdate();
      return count == 1;
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
