package com.capturis.tenkeyassessment.assessment.models;

import java.sql.Timestamp;

public class Assessment {

  private int assessmentId;
  private int userId;
  private Timestamp dateTaken;
  private int timeGiven;
  private int typeId;
  public int keystrokes;
  public int backspaces;
  public String[] inputValues;
  public String[] expectedValues;
  public int enterCount;

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

  public String[] getInputValues() { return this.inputValues; }

  public String[] getExpectedValues() { return this.expectedValues; }

  public int getKeystrokes() { return this.keystrokes; }

  public int getBackspaces() { return this.backspaces; }

  public int getEnterCount() { return this.enterCount; }

  public int lastCheck(String input, String expected)
  {
    int inputLength = input.length();
    int expectedLength = expected.length();
    int index = 0;
    int differences = 0;
    int smaller = Math.min(inputLength, expectedLength);
    while(index < smaller)
    {
      if(!(input.substring(index, index + 1).equals(expected.substring(index, index + 1))))
      {
        differences++;
        index++;
      }
      else
      {
        index++;
      }
    }
    return differences;
  }
  public int differences(String input, String expected)
  {
    int differences = 0;
    if(input.equals(expected))
    {
      return 0;
    }
    else
    {
      int inputLength = input.length();
      int expectedLength = expected.length();
      int index = 0;
      differences = Math.abs(inputLength - expectedLength);
      int smaller = Math.min(inputLength, expectedLength);
      while(index < smaller)
      {
        if(!(input.substring(index, index + 1).equals(expected.substring(index, index + 1))))
        {
          differences++;
          index++;
        }
        else
        {
          index++;
        }
      }
    }
    return differences;
  }
}
