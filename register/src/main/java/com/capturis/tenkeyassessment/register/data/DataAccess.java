/**
 * DAO for AssessmentUser
 */

package com.capturis.tenkeyassessment.register.data;

import com.capturis.tenkeyassessment.register.models.AssessmentUser;
import com.capturis.tenkeyassessment.register.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess extends Connection {

  private final Statement statement;

  @Inject
  public DataAccess() throws SQLException, IOException {
    this.statement = setupStatement();
  }

public AssessmentUser getUserById(int id) throws SQLException{

  String sql = "SELECT * FROM assessmentuser where userid = " + id;
  ResultSet rs = statement.executeQuery(sql);
  if (rs.next()) {
    return assessmentUserMap(rs);
  }
  else
  {
    return null;
  }

}

  public List<AssessmentUser> findAll() throws SQLException{

    List<AssessmentUser> list = new ArrayList<AssessmentUser>();
    String sql = "SELECT * FROM assessmentuser ORDER BY userid";

    ResultSet rs = statement.executeQuery(sql);
    while (rs.next()) {
      list.add(assessmentUserMap(rs));
    }

    return list;
  }

  public AssessmentUser create(AssessmentUser assessmentUser) throws SQLException, IOException {
    String sql = "INSERT INTO assessmentuser " +
      "(firstname, lastname, emailaddress, phonenumber, roleid, createddate, street, city, state, zipcode, country, jobcode)" +
      " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
      "RETURNING userid";
    String sql1 = "INSERT INTO userlogin (username, userid, passwordHash, accountlock_fl, lastlogindate) VALUES (?, ?, ?, ?, ?) RETURNING userloginid";

    //Try to insert record into assessmentuser table
    try {
    PreparedStatement ps = setupPreparedStatement(sql);

      ps.setString(1, assessmentUser.getFirstName());
      ps.setString(2, assessmentUser.getLastName());
      ps.setString(3, assessmentUser.getEmailAddress());
      ps.setString(4, assessmentUser.getPhoneNumber());
      ps.setInt(5, 1);
      ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
      ps.setString(7, assessmentUser.getStreet());
      ps.setString(8, assessmentUser.getCity());
      ps.setString(9, assessmentUser.getState());
      ps.setString(10, assessmentUser.getZipCode());
      ps.setString(11, assessmentUser.getCountry());
      ps.setString(12, assessmentUser.getJobCode());


      ResultSet rs = ps.executeQuery();

      //ResultSet rs = ps.getGeneratedKeys();


        rs.next();

        int id = rs.getInt(1);
        assessmentUser.setUserId(id);

        //Encrypt password and store in UserLogin table
      String hashedpw = BCrypt.hashpw(assessmentUser.getPasswordHash(), BCrypt.gensalt());
      PreparedStatement ps1 = setupPreparedStatement(sql1);
      ps1.setString(1, assessmentUser.getUsername().toLowerCase());
      ps1.setInt(2, id);
      ps1.setString(3, hashedpw);
      ps1.setBoolean(4, false);
      ps1.setTimestamp(5, null);

      ResultSet rs1 = ps1.executeQuery();


      rs1.next();

        int id1 = rs1.getInt(1);
        assessmentUser.setUserLoginId(id1);
    }
    catch (Exception e)
    {

      if(e instanceof RuntimeException)
      {
        throw e;
      }
      else {
        throw new RuntimeException(e);
      }
    }
    return assessmentUser;
  }


  public AssessmentUser update(AssessmentUser assessmentUser) throws SQLException, IOException {

    String sql = "UPDATE assessmentuser SET firstname = ?, lastname = ?, emailaddress = ?, phonenumber = ?, roleid = ?, street = ?, city = ?, state = ?, zipcode = ?, country = ? WHERE userid = ?";
    PreparedStatement ps = setupPreparedStatement(sql);

    ps.setString(1, assessmentUser.getFirstName());
    ps.setString(2, assessmentUser.getLastName());
    ps.setString(3, assessmentUser.getEmailAddress());
    ps.setString(4, assessmentUser.getPhoneNumber());
    ps.setInt(5, assessmentUser.getRoleId());
    ps.setString(6, assessmentUser.getStreet());
    ps.setString(7, assessmentUser.getCity());
    ps.setString(8, assessmentUser.getState());
    ps.setString(9, assessmentUser.getZipCode());
    ps.setString(10, assessmentUser.getCountry());
    ps.setInt(11, assessmentUser.getUserId());

    ps.executeUpdate();

    return assessmentUser;
  }

  public boolean remove(int id) throws SQLException, IOException {
    String sql = "DELETE FROM asse where userloginid = ?";
    PreparedStatement ps = setupPreparedStatement(sql);
    ps.setInt(1, id);

    int count = ps.executeUpdate();
    return count == 1;
  }

  /**
   * AssessmentUser mapper method
   * @param rs
   * @return assessmentUser
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
