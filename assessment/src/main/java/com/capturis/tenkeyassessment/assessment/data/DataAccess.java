package com.capturis.tenkeyassessment.assessment.data;

import com.capturis.tenkeyassessment.assessment.models.Assessment;
import com.capturis.tenkeyassessment.assessment.sql.Connection;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;
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

  public Assessment getAssessmentByUserId(int id) throws SQLException{

    String sql = "SELECT * FROM assessment where userid = " + id;
    ResultSet rs = statement.executeQuery(sql);
    if (rs.next()) {
      return mapAssessment(rs);
    }
    else
    {
      return null;
    }

  }
  public List<Assessment> findAll() throws SQLException{

    List<Assessment> list = new ArrayList<Assessment>();
    String sql = "SELECT * FROM assessment ORDER BY userid";

    ResultSet rs = statement.executeQuery(sql);
    while (rs.next()) {
      list.add(mapAssessment(rs));
    }

    return list;
  }

  public Assessment create(Assessment assessment) throws SQLException, IOException {
    String sql = "INSERT INTO assessment (userid, datetaken, timegiven, typeid) VALUES (?, ?, ?, ?)";
    PreparedStatement ps = setupPreparedStatement(sql);

    ps.setInt(1, assessment.getUserId());
    ps.setTimestamp(2, assessment.getDateTaken());
    ps.setInt(3, assessment.getTimeGiven());
    ps.setInt(4, assessment.getTypeId());

    ResultSet rs = ps.getGeneratedKeys();

    rs.next();

    int id = rs.getInt(1);
    assessment.setAssessmentId(id);

    return assessment;

  }

  public Assessment update(Assessment assessment) throws SQLException, IOException {

    String sql = "UPDATE assessment SET datetaken = ?, timegiven= ?, typeid= ? WHERE userid = ?";
    PreparedStatement ps = setupPreparedStatement(sql);

    ps.setTimestamp(1, assessment.getDateTaken());
    ps.setInt(2, assessment.getTimeGiven());
    ps.setInt(3, assessment.getTypeId());
    ps.setInt(4, assessment.getUserId());

    ps.executeUpdate();

    return assessment;
  }

  public boolean remove(int id) throws SQLException, IOException {
    String sql = "DELETE FROM assessment where assessmentid = ?";
    PreparedStatement ps = setupPreparedStatement(sql);
    ps.setInt(1, id);

    int count = ps.executeUpdate();
    return count == 1;
  }

  public int saveAssessment(Assessment a) throws SQLException
  {
    String sql = "INSERT INTO assessment (userid, datetaken, timegiven, typeid) VALUES ("
      + a.getUserId() + ", " + a.getDateTaken() + ", " + a.getTimeGiven() + ", " + a.getTypeId() + ")";

    ResultSet rs = statement.executeQuery(sql);
    if(rs.next())
    {
      String sql2 = "SELECT assessmentid from assessment where userid = " + a.getUserId() + " AND typeid = "
      + a.getTypeId() + " ORDER BY id DESC LIMIT 1";

      ResultSet rs2 = statement.executeQuery(sql2);
      if(rs.next())
      {
        return rs2.getInt(0);
      }
      else
      {
        return -1;
      }
    }
    else
    {
      return -1;
    }

  }

  public void saveAssessmentResult(AssessmentResult result) throws SQLException
  {
    String sql = "INSERT INTO assessmentresult (userid, assessmentid, perfectcount, uncorrectedmistakes, backspacepresscount, " +
      "kph, accuracy, linescompleted, totalkeystrokes) VALUES (" + result.getUserId() + ", " + result.getAssessmentId() + ", " + result.getPerfectCount()
      + ", " + result.getUnCorrectedMistakes() + ", " + result.getBackspacePressCount() + ", " + result.getKph() + ", " + result.getAccuracy() + ", "
      + result.getLinesCompleted() + ", " + result.getTotalKeyStrokes() + ")";

    ResultSet rs = statement.executeQuery(sql);
  }

  private Assessment mapAssessment(ResultSet rs) throws SQLException{

    int assessmentId = rs.getInt("assessmentid");
    int userId = rs.getInt("userid");
    Timestamp dateTaken = rs.getTimestamp("datetaken");
    int timeGiven = rs.getInt("timegiven");
    int typeId = rs.getInt("typeid");

    Assessment assessment = new Assessment();
    assessment.setAssessmentId(assessmentId);
    assessment.setUserId(userId);
    assessment.setDateTaken(dateTaken);
    assessment.setTimeGiven(timeGiven);
    assessment.setTypeId(typeId);

    return assessment;
  }
}
