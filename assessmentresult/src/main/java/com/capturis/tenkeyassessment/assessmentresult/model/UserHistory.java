package com.capturis.tenkeyassessment.assessmentresult.model;

import java.sql.Timestamp;

public class UserHistory {

  private int userId;
  private int assessmentId;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getAssessmentId() {
    return assessmentId;
  }

  public void setAssessmentId(int assessmentId) {
    this.assessmentId = assessmentId;
  }

  private Timestamp dateTaken;
  private String assessmentType;
  private int kph;
  private double accuracy;
  private int backspacePressCount;
  private int mistakes;

  public UserHistory() {
  }

  public Timestamp getDateTaken() {
    return dateTaken;
  }

  public void setDateTaken(Timestamp dateTaken) {
    this.dateTaken = dateTaken;
  }

  public String getAssessmentType() {
    return assessmentType;
  }

  public void setAssessmentType(String assessmentType) {
    this.assessmentType = assessmentType;
  }

  public int getKph() {
    return kph;
  }

  public void setKph(int kph) {
    this.kph = kph;
  }

  public double getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(double accuracy) {
    this.accuracy = accuracy;
  }

  public int getBackspacePressCount() {
    return backspacePressCount;
  }

  public void setBackspacePressCount(int backspacePressCount) {
    this.backspacePressCount = backspacePressCount;
  }

  public int getMistakes() {
    return mistakes;
  }

  public void setMistakes(int mistakes) {
    this.mistakes = mistakes;
  }
}
