/**
 * DAO for UserLogin
 */

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

  /**
   * Used to authenticate users
   * @param username
   * @param password
   * @return AssessmentUser if finds username/password combo. Otherwise null
   * @throws SQLException
   * @throws IOException
   */
  public AssessmentUser authenticate(String username, String password) throws SQLException, IOException {
      String sql = "SELECT * FROM userlogin WHERE username=?"; //Check if username.toLowerCase exists in DB
    ps = setupPreparedStatement(sql);
      ps.setString(1, username.toLowerCase());
    ResultSet rs = ps.executeQuery();
      if(rs.next()){ //If username exists
        UserLogin userLogin = userLoginMap(rs);
        String sql1 = "SELECT * FROM userlogin WHERE userid = ?"; //Grab userId associated with username that matched
        ps = setupPreparedStatement(sql1);
        ps.setInt(1, userLogin.getUserId());
        ResultSet rs1 = ps.executeQuery();
        if(rs1.next()) {
          UserLogin ul2 = userLoginMap(rs1);
          String plainPass = password;
          if(BCrypt.checkpw(plainPass, ul2.getPasswordHash())) //Check if passwords match
          {
            String sql2 = "SELECT * FROM assessmentuser WHERE userid = ?"; //Get AssessmentUser object based on userId previously found
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

  /**
   * UserLogin mapper method
   * @param rs
   * @return UserLogin
   * @throws SQLException
   */
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

  /**
   * AssessmentUser mapper method
   * @param rs
   * @return AssessmentUser
   * @throws SQLException
   */
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
