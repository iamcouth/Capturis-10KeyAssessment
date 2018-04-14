package com.capturis.tenkeyassessment.register.data;

import com.capturis.tenkeyassessment.register.models.AssessmentUser;
import com.capturis.tenkeyassessment.register.sql.Connection;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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
