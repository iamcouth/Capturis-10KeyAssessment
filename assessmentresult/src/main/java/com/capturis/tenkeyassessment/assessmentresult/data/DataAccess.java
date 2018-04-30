package com.capturis.tenkeyassessment.assessmentresult.data;

import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;
import com.capturis.tenkeyassessment.assessmentresult.model.ManagerSummary;
import com.capturis.tenkeyassessment.assessmentresult.model.UserHistory;
import com.capturis.tenkeyassessment.assessmentresult.sql.Connection;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
        return mapAssessmentResult(rs);
      }
      else {
        return null;
      }

    }

    public List<UserHistory> getHistoryByUserId(int id) throws SQLException {

      List<UserHistory> list = new ArrayList<UserHistory>();
      String sql = "SELECT a.userid, a.assessmentid, a.datetaken, at.assessmentname, ar.kph, ar.accuracy, ar.backspacepresscount, ar.uncorrectedmistakes " +
        "FROM assessment a " +
        "JOIN assessmentresult ar on ar.userid = a.userid and a.assessmentid = ar.assessmentid " +
        "JOIN assessmenttype at on at.assessmenttypeid = a.typeid where a.userid = " + id;
      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        list.add(mapUserHistory(rs));
      }

      return list;

    }

    public List<AssessmentResult> findAll() throws SQLException {

      List<AssessmentResult> list = new ArrayList<AssessmentResult>();
      String sql = "SELECT * FROM assessmentresult ORDER BY userid";

      ResultSet rs = statement.executeQuery(sql);
      if (rs != null) {
        while (rs.next()) {
          list.add(mapAssessmentResult(rs));
        }

        return list;
      } else return null;
    }

    public List<ManagerSummary> findAllManager() throws SQLException {

      List<ManagerSummary> list = new ArrayList<ManagerSummary>();
      String sql = "SELECT (au.firstname || ' ' || au.lastname) as name" +
        ",ar.kph" +
        ",ar.accuracy" +
        ",au.jobcode" +
        ",att.assessmentname" +
        ",a.timegiven" +
        ",a.datetaken " +
        ",ar.linescompleted " +
        "FROM assessmentuser as au " +
        "JOIN assessment as a " +
        "ON a.userid = au.userid " +
        "JOIN assessmentresult as ar " +
        "ON ar.assessmentid = a.assessmentid " +
        "JOIN assessmenttype as att " +
        "ON att.assessmenttypeid = a.typeid " +
        "ORDER BY a.assessmentid";

      ResultSet rs = statement.executeQuery(sql);
      while (rs.next()) {
        list.add(mapManagerSummary(rs));
      }

      return list;
    }

    public AssessmentResult create(AssessmentResult assessmentResult) throws SQLException, IOException {
      String sql = "INSERT INTO assessmentresult (userid, assessmentid, perfectcount, uncorrectedmistakes, backspacepresscount, kph, accuracy, linescompleted, totalkeystrokes, correctedmistakes) VALUES (?, ?, ?, ? ,? ,? ,? ,? ,? ,?)";
      PreparedStatement ps = setupPreparedStatement(sql);

      ps.setInt(1, assessmentResult.getUserId());
      ps.setInt(2, assessmentResult.getAssessmentId());
      ps.setInt(3, assessmentResult.getPerfectCount());
      ps.setInt(4, assessmentResult.getUnCorrectedMistakes());
      ps.setInt(5, assessmentResult.getBackspacePressCount());
      ps.setInt(6, assessmentResult.getKph());
      ps.setDouble(7, assessmentResult.getAccuracy());
      ps.setInt(8, assessmentResult.getLinesCompleted());
      ps.setInt(9, assessmentResult.getTotalKeyStrokes());
      ps.setInt(10, assessmentResult.getCorrectedMistakes());

      ResultSet rs = ps.getGeneratedKeys();

      rs.next();

      int id = rs.getInt(1);
      assessmentResult.setAssessmentResultId(id);

      return assessmentResult;

    }

    public AssessmentResult update(AssessmentResult assessmentResult) throws SQLException, IOException {

      String sql = "UPDATE assessmentresult SET perfectcount = ?, uncorrectedmistakes = ?, backspacepresscount = ?, kph = ?, accuracy = ?, linescompleted = ?, totalkeystrokes = ?, correctedmistakes = ? WHERE userid = ? AND assessmentid = ?";
      PreparedStatement ps = setupPreparedStatement(sql);

      ps.setInt(1, assessmentResult.getPerfectCount());
      ps.setInt(2, assessmentResult.getUnCorrectedMistakes());
      ps.setInt(3, assessmentResult.getBackspacePressCount());
      ps.setInt(4, assessmentResult.getKph());
      ps.setDouble(5, assessmentResult.getAccuracy());
      ps.setInt(6, assessmentResult.getLinesCompleted());
      ps.setInt(7, assessmentResult.getTotalKeyStrokes());
      ps.setInt(8, assessmentResult.getCorrectedMistakes());
      ps.setInt(9, assessmentResult.getUserId());
      ps.setInt(10, assessmentResult.getAssessmentId());

      ps.executeUpdate();

      return assessmentResult;
    }

    public boolean remove(int id) throws SQLException, IOException {
      String sql = "DELETE FROM assessmentresults where assessmentresultid = ?";
      PreparedStatement ps = setupPreparedStatement(sql);
      ps.setInt(1, id);

      int count = ps.executeUpdate();
      return count == 1;
    }

    public AssessmentResult getResults(int id) throws SQLException {
      String sql = "SELECT * FROM assessmentresult where assessmentid = " + id;
      ResultSet rs = statement.executeQuery(sql);
      if (rs.next()) {
        return mapAssessmentResult(rs);
      } else {
        return null;
      }
    }

    private AssessmentResult mapAssessmentResult(ResultSet rs) throws SQLException {

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

    private ManagerSummary mapManagerSummary(ResultSet rs) throws SQLException {

      String name = rs.getString("name");
      int kph = rs.getInt("kph");
      double accuracy = rs.getDouble("accuracy");
      String jobCode = rs.getString("jobcode");
      String assessmentName = rs.getString("assessmentname");
      int timeGiven = rs.getInt("timegiven");
      Timestamp dateTaken = rs.getTimestamp("datetaken");
      int linesCompleted = rs.getInt("linescompleted");

      ManagerSummary managerSummary = new ManagerSummary();

      managerSummary.setName(name);
      managerSummary.setKph(kph);
      managerSummary.setAccuracy(accuracy);
      managerSummary.setJobCode(jobCode);
      managerSummary.setAssessmentName(assessmentName);
      managerSummary.setTimeGiven(timeGiven);
      managerSummary.setDateTaken(dateTaken);
      managerSummary.setLinesCompleted(linesCompleted);

      return managerSummary;
    }

    private UserHistory mapUserHistory(ResultSet rs) throws SQLException {

      int userId = rs.getInt("userid");
      int assessmentId = rs.getInt("assessmentid");
      Timestamp dateTaken = rs.getTimestamp("datetaken");
      String assessmentType = rs.getString("assessmentname");
      int kph = rs.getInt("kph");
      double accuracy = rs.getDouble("accuracy");
      int backspacePressCount = rs.getInt("backspacepresscount");
      int mistakes = rs.getInt("uncorrectedmistakes");
      UserHistory userHistory = new UserHistory();

      userHistory.setUserId(userId);
      userHistory.setAssessmentId(assessmentId);
      userHistory.setDateTaken(dateTaken);
      userHistory.setAssessmentType(assessmentType);
      userHistory.setKph(kph);
      userHistory.setAccuracy(accuracy);
      userHistory.setBackspacePressCount(backspacePressCount);
      userHistory.setMistakes(mistakes);

      return userHistory;
    }
  }
