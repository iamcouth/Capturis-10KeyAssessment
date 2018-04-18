package com.capturis.tenkeyassessment.login.data;

import com.capturis.tenkeyassessment.login.model.UserLogin;
import com.capturis.tenkeyassessment.login.sql.Connection;
import com.capturis.tenkeyassessment.register.models.AssessmentUser;
import org.mindrot.jbcrypt.BCrypt;

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

  public AssessmentUser authenticate(String username, String password) throws SQLException, IOException {
      String sql = "SELECT * FROM userlogin WHERE username=?";
    ps = setupPreparedStatement(sql);
      ps.setString(1, username);
    ResultSet rs = ps.executeQuery();
      if(rs.next()){
        UserLogin userLogin = userLoginMap(rs);
        String sql1 = "SELECT * FROM userlogin WHERE userid = ?";
        ps = setupPreparedStatement(sql1);
        ps.setInt(1, userLogin.getUserId());
        ResultSet rs1 = ps.executeQuery();
        if(rs1.next()) {
          UserLogin ul2 = userLoginMap(rs1);
          String plainPass = password;
          if(BCrypt.checkpw(plainPass, ul2.getPasswordHash()))
          {
            String sql2 = "SELECT * FROM assessmentuser WHERE userid = ?";
            ps = setupPreparedStatement(sql2);
            ps.setInt(1, ul2.getUserId());
            ResultSet rs2 = ps.executeQuery();
            if(rs2.next()) {
              AssessmentUser au = assessmentUserMap(rs2);
              return au;
            }
          }
        }
        else {
          return null;
        }

      }
      return null;
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

//  public UserLogin save(UserLogin userLogin) throws SQLException, IOException {
//      return userLogin.getUserId() > 0 ? update(userLogin) : create(userLogin);
//  }

//  public UserLogin create(UserLogin userLogin) throws SQLException, IOException {
//    String sql = "INSERT INTO userlogin (username, userid, passwordhash, accountlock_fl, lastlogindate) VALUES (?, ?, ?, ?, ?)";
//      ps = setupPreparedStatement(sql);
//
////    String hashedpw = BCrypt.hashpw(userLogin.getPasswordHash(), BCrypt.gensalt());
//
//      ps.setString(1, userLogin.getUsername());
//      ps.setInt(2, userLogin.getUserId());
//      ps.setString(3, userLogin.getPasswordHash());
//      ps.setBoolean(4, userLogin.isAccountLockFl());
//      ps.setTimestamp(5, userLogin.getLastLoginDate());
//
//      ps.executeUpdate();
//      ResultSet rs = ps.getGeneratedKeys();
//
//      if(rs.next()) {
//
//        int id = rs.getInt(1);
//        userLogin.setUserLoginId(id);
//
//        return userLogin;
//      }
//
//      else return null;
//  }

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
  private AssessmentUser assessmentUserMap(ResultSet rs) throws  SQLException{

    int userId = rs.getInt("userid");
    String firstName = rs.getString("firstname");
    String lastName = rs.getString("lastname");
    String emailAddress = rs.getString("emailaddress");
    String phoneNumber = rs.getString("phonenumber");
    int roleId = rs.getInt("roleid");
    Timestamp createdDate = rs.getTimestamp("createddate");
    String street = rs.getString("street");
    String city = rs.getString("city");
    String state = rs.getString("state");
    String zipCode = rs.getString("zipcode");
    String country = rs.getString("country");
    String jobCode = rs.getString("jobcode");

    AssessmentUser assessmentUser = new AssessmentUser();
    assessmentUser.setUserId(userId);
    assessmentUser.setFirstName(firstName);
    assessmentUser.setLastName(lastName);
    assessmentUser.setEmailAddress(emailAddress);
    assessmentUser.setPhoneNumber(phoneNumber);
    assessmentUser.setRoleId(roleId);
    assessmentUser.setCreatedDate(createdDate);
    assessmentUser.setStreet(street);
    assessmentUser.setCity(city);
    assessmentUser.setState(state);
    assessmentUser.setZipCode(zipCode);
    assessmentUser.setCountry(country);
    assessmentUser.setJobCode(jobCode);

    return assessmentUser;
  }

}
