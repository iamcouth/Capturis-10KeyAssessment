package com.capturis.tenkeyassessment.assessmentresult.model;

import java.sql.Timestamp;

public class ManagerSummary {

  String name;
  int kph;
  String accuracy;
  String jobCode;
  String assessmentName;
  int timeGiven;
  Timestamp dateTaken;

  public ManagerSummary() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKph() {
    return kph;
  }

  public void setKph(int kph) {
    this.kph = kph;
  }

  public String getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(String accuracy) {
    this.accuracy = accuracy;
  }

  public String getJobCode() {
    return jobCode;
  }

  public void setJobCode(String jobCode) {
    this.jobCode = jobCode;
  }

  public String getAssessmentName() {
    return assessmentName;
  }

  public void setAssessmentName(String assessmentName) {
    this.assessmentName = assessmentName;
  }

  public int getTimeGiven() {
    return timeGiven;
  }

  public void setTimeGiven(int timeGiven) {
    this.timeGiven = timeGiven;
  }

  public Timestamp getDateTaken() {
    return dateTaken;
  }

  public void setDateTaken(Timestamp dateTaken) {
    this.dateTaken = dateTaken;
  }
}
