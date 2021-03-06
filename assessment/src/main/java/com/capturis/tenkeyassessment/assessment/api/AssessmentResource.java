/**
 *
 * Backend AssessmentResource. Sends data utilizing the DAO.
 */

package com.capturis.tenkeyassessment.assessment.api;

import com.capturis.tenkeyassessment.assessment.data.DataAccess;
import com.capturis.tenkeyassessment.assessment.models.Assessment;
import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("assessment")
public class AssessmentResource {

  private final DataAccess dataAccess;

  @Inject
  public AssessmentResource (DataAccess dataAccess) {this.dataAccess = dataAccess;}

  /**
   * Getting the assessment by UserId
   * @param id
   * @return Assessment
   */
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
  @Path("/newassessment")
  public int getNewAssessment(Assessment t)
  {
    int returnID = 0;
    try {
      returnID = dataAccess.saveAssessment(t);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return returnID;
  }

  /**
   * Getting all Assessments
   * @return List of Assessment objects
   */
  @GET
  @Path("/all/assessments")
  public List<Assessment> findAll() {
    try {
      return dataAccess.findAll();
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return  null;
  }

  /**
   * Creating new assessment
   * @param assessment
   * @return assessment
   */
  @POST
  public Assessment create(Assessment assessment) {
    try {
      return dataAccess.create(assessment);
    }
    catch (SQLException | IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Used for updating an assessment
   * @param assessment
   * @return assessment
   */
  @PUT
  @Path("/{id}")
  public Assessment update(Assessment assessment) {
    try {
      dataAccess.update(assessment);
      return assessment;
    }
    catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
    return null;
  }

  /**
   * Used for deleting an assessment
   * @param id
   */
  @DELETE
  @Path("/{id}")
  public void remove (@PathParam("id") int id) {
    try {
      dataAccess.remove(id);
    } catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
  }

  /**
   * Used for processing the results coming from user input on frontend
   * @param t
   * @return
   */
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

    AssessmentResult a = new AssessmentResult();
    a.setBackspacePressCount(backspaces);
    a.setKph(KPH);
    a.setTotalKeyStrokes(keystrokes);
    a.setUserId(t.getUserId());
    a.setPerfectCount(0);
    a.setUnCorrectedMistakes(0);
    a.setLinesCompleted(0);
    a.setAssessmentId(t.getAssessmentId());

    a.setUnCorrectedMistakes(errors);

    if(keystrokes == 0)
    {
      a.setAccuracy(0);
    }
    else
    {
      System.out.println("keystrokes" + keystrokes);
      System.out.println("errors" + errors);
      double errs = (double) errors;
      double keys = (double) keystrokes;
      double accuracy = (keys/(keys + errs))*100;
      System.out.println("accuracy" + accuracy);
      a.setAccuracy(accuracy);
    }

    try {
      dataAccess.saveAssessmentResult(a);
    } catch (Exception e) {
      System.out.println(e.getMessage() + " Save result error");
    }

    return returnID;
  }

}
