package com.capturis.tenkeyassessment.assessmentresult.model;

public class AssessmentResult {

  private int assessmentResultId;
  private int userId;
  private int assessmentId;
  private int perfectCount;
  private int unCorrectedMistakes;
  private int backspacePressCount;
  private int kph;
  private String accuracy;
  private int linesCompleted;
  private int totalKeyStrokes;
  private int correctedMistakes;

  public AssessmentResult() {
  }

  public int getAssessmentResultId() {
    return assessmentResultId;
  }

  public void setAssessmentResultId(int assessmentResultId) {
    this.assessmentResultId = assessmentResultId;
  }

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

  public int getPerfectCount() {
    return perfectCount;
  }

  public void setPerfectCount(int perfectCount) {
    this.perfectCount = perfectCount;
  }

  public int getUnCorrectedMistakes() {
    return unCorrectedMistakes;
  }

  public void setUnCorrectedMistakes(int unCorrectedMistakes) {
    this.unCorrectedMistakes = unCorrectedMistakes;
  }

  public int getBackspacePressCount() {
    return backspacePressCount;
  }

  public void setBackspacePressCount(int backspacePressCount) {
    this.backspacePressCount = backspacePressCount;
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

  public int getLinesCompleted() {
    return linesCompleted;
  }

  public void setLinesCompleted(int linesCompleted) {
    this.linesCompleted = linesCompleted;
  }

  public int getTotalKeyStrokes() {
    return totalKeyStrokes;
  }

  public void setTotalKeyStrokes(int totalKeyStrokes) {
    this.totalKeyStrokes = totalKeyStrokes;
  }

  public int getCorrectedMistakes() {
    return correctedMistakes;
  }

  public void setCorrectedMistakes(int correctedMistakes) {
    this.correctedMistakes = correctedMistakes;
  }
}
