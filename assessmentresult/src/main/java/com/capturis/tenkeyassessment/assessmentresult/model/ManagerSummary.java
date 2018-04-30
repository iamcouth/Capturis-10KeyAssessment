package com.capturis.tenkeyassessment.assessmentresult.model;

import java.sql.Timestamp;

public class ManagerSummary {

  private String name;
  private int kph;
  private double accuracy;
  private String jobCode;
  private String assessmentName;
  private int timeGiven;
  private Timestamp dateTaken;
  private int linesCompleted;


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

  public double getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(double accuracy) {
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

  public int getLinesCompleted() { return linesCompleted; }

  public void setLinesCompleted(int linesCompleted) { this.linesCompleted = linesCompleted; }
}
