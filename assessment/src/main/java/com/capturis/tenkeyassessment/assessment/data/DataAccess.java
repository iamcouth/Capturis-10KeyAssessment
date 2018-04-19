package com.capturis.tenkeyassessment.assessment.data;

import com.capturis.tenkeyassessment.assessment.models.Assessment;
import com.capturis.tenkeyassessment.assessment.sql.Connection;
import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static org.postgresql.jdbc.EscapedFunctions.INSERT;

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
