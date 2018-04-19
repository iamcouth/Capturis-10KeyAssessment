package com.capturis.tenkeyassessment.assessment.api;

import com.capturis.tenkeyassessment.assessment.data.DataAccess;
import com.capturis.tenkeyassessment.assessment.models.Assessment;
import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;




@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("assessment")
public class AssessmentResource {

  private final DataAccess dataAccess;

  @Inject
  public AssessmentResource (DataAccess dataAccess) {this.dataAccess = dataAccess;}

  @GET
  @Path("/{id}")
  public Assessment getAssessmentById(@PathParam("id") int id) {
    try{
      return dataAccess.getAssessmentByUserId(id);
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
    return null;
  }

  @POST
  @Path("/process")
  public int processResults(Assessment t)
  {
    String[] input = t.getInputValues();
    String[] expected = t.getExpectedValues();
    int keystrokes = t.getKeystrokes();
    int backspaces = t.getBackspaces();
    int enterCount = t.getEnterCount();
    int errors = 0;
    int index = 0;
    while(index < enterCount - 1)
    {
      errors += t.differences(input[index], expected[index]);
      index++;
    }
    errors += t.lastCheck(input[enterCount-1], expected[enterCount-1]);
    int KPH = keystrokes * (60/t.getTimeGiven());
    int returnID = 0;
    try {
      returnID = dataAccess.saveAssessment(t);
    }
    catch (Exception e) {
      System.out.println(e.getMessage() + " save assessment error");
    }

    AssessmentResult a = new AssessmentResult();
    a.setBackspacePressCount(backspaces);
    a.setKph(KPH);
    a.setTotalKeyStrokes(keystrokes);
    a.setUserId(t.getUserId());
    a.setPerfectCount(0);
    a.setUnCorrectedMistakes(0);
    a.setLinesCompleted(0);
    a.setAssessmentId(returnID);

    double keys = (double) keystrokes;
    double errs = (double) errors;
    System.out.println("errors: " + errs + "------- keystrokes: " + keys);
    if(errors >= keystrokes)
    {
      a.setAccuracy(0);
    }
    else
    {
      a.setAccuracy(((keys-errs)/keys)*100);
    }
    System.out.println("Acc" + (keys-errs)/keys);
    System.out.println(a.getAccuracy());
    System.out.println("KPH" + KPH);
    System.out.println(a.getKph());
    System.out.println("keystrokes" + keystrokes);
    System.out.println(a.getTotalKeyStrokes());

    try {
      dataAccess.saveAssessmentResult(a);
    } catch (Exception e) {
      System.out.println(e.getMessage() + " Save result error");
    }

    return returnID;
    //return a.getAssessmentId();
  }

}
