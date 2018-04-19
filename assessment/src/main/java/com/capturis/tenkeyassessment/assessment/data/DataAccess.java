package com.capturis.tenkeyassessment.assessment.data;

import com.capturis.tenkeyassessment.assessment.models.Assessment;
import com.capturis.tenkeyassessment.assessment.sql.Connection;
import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.*;

public class DataAccess extends Connection {

  private final Statement statement;
  private PreparedStatement ps;

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

  public int saveAssessment(Assessment a) throws SQLException, IOException
  {
    String sql = "INSERT INTO assessment (userid, datetaken, timegiven, typeid) " +
      "VALUES (?, ?, ?, ?) RETURNING assessmentid";

    try {
      ps = setupPreparedStatement(sql);
      ps.setInt(1, a.getUserId());
      ps.setTimestamp(2, a.getDateTaken());
      ps.setInt(3, a.getTimeGiven());
      ps.setInt(4, a.getTypeId());

      ResultSet rs = ps.executeQuery();
      rs.next();
      int id = rs.getInt(1);
      a.setAssessmentId(id);
      System.out.println(id);
      return id;
    }
    catch (Exception e) {
      if(e instanceof RuntimeException) {
        throw e;
      }
      else {
        throw new RuntimeException(e);
      }
    }
  }

  public void saveAssessmentResult(AssessmentResult result) throws SQLException, IOException {
    String sql = "INSERT INTO assessmentresult (userid, assessmentid, perfectcount, uncorrectedmistakes," +
      " backspacepresscount, kph, accuracy, linescompleted, totalkeystrokes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      ps = setupPreparedStatement(sql);
      ps.setInt(1, result.getUserId());
      ps.setInt(2, result.getAssessmentId());
      ps.setInt(3, result.getPerfectCount());
      ps.setInt(4, result.getUnCorrectedMistakes());
      ps.setInt(5, result.getBackspacePressCount());
      ps.setInt(6, result.getKph());
      ps.setDouble(7, result.getAccuracy());
      ps.setInt(8, result.getLinesCompleted());
      ps.setInt(9, result.getTotalKeyStrokes());

      ResultSet rs = ps.executeQuery();
      rs.next();
    } catch (Exception e) {
      if (e instanceof RuntimeException) {
        throw e;
      } else {
        throw new RuntimeException(e);
      }
    }
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
