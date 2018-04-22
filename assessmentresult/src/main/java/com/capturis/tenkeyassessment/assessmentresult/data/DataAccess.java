package com.capturis.tenkeyassessment.assessmentresult.data;

import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;
import com.capturis.tenkeyassessment.assessmentresult.sql.Connection;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


  public class DataAccess extends Connection {

    private final Statement statement;
    private PreparedStatement ps;

    @Inject
    public DataAccess() throws SQLException, IOException {
      this.statement = setupStatement();
    }


    public AssessmentResult getAssessmentByUserId(int id) throws SQLException {

      String sql = "SELECT * FROM assessmentresult where userid = " + id;
      ResultSet rs = statement.executeQuery(sql);
      if(rs.next()) {
        return assessmentResultMap(rs);
      }
      else {
        return null;
      }

    }
    public AssessmentResult getResults(int id) throws SQLException
    {
      String sql = "SELECT * FROM assessmentresult where assessmentid = " + id;
      ResultSet rs = statement.executeQuery(sql);
      if(rs.next())
      {
        return assessmentResultMap(rs);
      }
      else
      {
        return null;
      }
    }

    private AssessmentResult assessmentResultMap(ResultSet rs) throws SQLException{

      int assessmentResultId = rs.getInt("assessmentresultid");
      int userId = rs.getInt("userid");
      int assessmentId = rs.getInt("assessmentid");
      int perfectCount = rs.getInt("perfectcount");
      int unCorrectedMistakes = rs.getInt("uncorrectedmistakes");
      int backspacePressCount = rs.getInt("backspacepresscount");
      int kph = rs.getInt("kph");
      double accuracy = rs.getDouble("accuracy");
      int linesCompleted = rs.getInt("linescompleted");
      int totalKeyStrokes = rs.getInt("totalkeystrokes");
      int correctedMistakes = rs.getInt("correctedmistakes");

      AssessmentResult assessmentResult = new AssessmentResult();

      assessmentResult.setAssessmentResultId(assessmentResultId);
      assessmentResult.setUserId(userId);
      assessmentResult.setAssessmentId(assessmentId);
      assessmentResult.setPerfectCount(perfectCount);
      assessmentResult.setUnCorrectedMistakes(unCorrectedMistakes);
      assessmentResult.setBackspacePressCount(backspacePressCount);
      assessmentResult.setKph(kph);
      assessmentResult.setAccuracy(accuracy);
      assessmentResult.setLinesCompleted(linesCompleted);
      assessmentResult.setTotalKeyStrokes(totalKeyStrokes);
      assessmentResult.setCorrectedMistakes(correctedMistakes);

      return assessmentResult;
    }

  }
