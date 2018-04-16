package com.capturis.tenkeyassessment.assessment.models;

import java.sql.Timestamp;

public class Assessment {

  private int assessmentId;
  private int userId;
  private Timestamp dateTaken;
  private int timeGiven;
  private int typeId;

  public Assessment(int assessmentId, int userId, Timestamp dateTaken, int timeGiven, int typeId) {
    this.assessmentId = assessmentId;
    this.userId = userId;
    this.dateTaken = dateTaken;
    this.timeGiven = timeGiven;
    this.typeId = typeId;
  }
  public Assessment() { }

  public int getAssessmentId() {
    return assessmentId;
  }

  public void setAssessmentId(int assessmentId) {
    this.assessmentId = assessmentId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Timestamp getDateTaken() {
    return dateTaken;
  }

  public void setDateTaken(Timestamp dateTaken) {
    this.dateTaken = dateTaken;
  }

  public int getTimeGiven() {
    return timeGiven;
  }

  public void setTimeGiven(int timeGiven) {
    this.timeGiven = timeGiven;
  }

  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }
}
