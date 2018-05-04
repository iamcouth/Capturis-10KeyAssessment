/**
 *
 * Backend AssessmentResultResource. Sends data utilizing the DAO.
 */

package com.capturis.tenkeyassessment.assessmentresult.api;

import com.capturis.tenkeyassessment.assessmentresult.data.DataAccess;
import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;
import com.capturis.tenkeyassessment.assessmentresult.model.ManagerSummary;
import com.capturis.tenkeyassessment.assessmentresult.model.UserHistory;

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
@Path("assessmentresults")
public class AssessmentResultResource {

  private final DataAccess dataAccess;

  @Inject
  public AssessmentResultResource (DataAccess dataAccess) { this.dataAccess = dataAccess; }

  /**
   * Getting assessmentresult by UserId
   * @param id
   * @return assessmentResult
   */
  @GET
  @Path("/{id}")
  public AssessmentResult getAssessmentResultByUserId(@PathParam("id") int id) {
    try{
      return dataAccess.getAssessmentByUserId(id);
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Getting all assessmentresults
   * @return List of assessmentresults
   */
  @GET
  @Path("/all/assessmentresults")
  public List<AssessmentResult> findAll() {
    try {
      return dataAccess.findAll();
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Used for generating data on the User-Dashboard: User History table
   * @param id
   * @return
   */
  @GET
  @Path("/all/userhistory/{id}")
  public List<UserHistory> findUserHistoryById(@PathParam("id") int id) {
    try {
      return dataAccess.getHistoryByUserId(id);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Used for creating an assessmentresult
   * @param assessmentResult
   * @return assessmentResult
   */
  @POST
  public AssessmentResult create(AssessmentResult assessmentResult) {
    try {
      return dataAccess.create(assessmentResult);
    } catch (SQLException | IOException e) {
    }
    return null;
  }

  /**
   * Used for getting results of an assessmentresult
   * @param assessmentID
   * @return assessmentresult
   */
  @GET
  @Path("/getResults/{id}")
  public AssessmentResult getResults(@PathParam("id") int assessmentID)
  {
    try
    {
      return dataAccess.getResults(assessmentID);
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Used for updating an AssessmentResult
   * @param assessmentResult
   * @return assessmentResult
   */
  @PUT
  @Path("/{id}")
  public AssessmentResult update(AssessmentResult assessmentResult) {
    try {
      dataAccess.update(assessmentResult);
      return assessmentResult;
    }
    catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
    return null;
  }

  /**
   * Used for deleting an AssessmentResult
   * @param id
   */
  @DELETE
  @Path("/{id}")
  public void remove (@PathParam("id") int id) {
    try {
      dataAccess.remove(id);
    }
    catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
  }

  /**
   * Used to display data on the manager page
   * @return
   */
  @GET
  @Path("/all/manager")
  public List<ManagerSummary> findAllManager() {
    try {
      return dataAccess.findAllManager();
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return  null;
  }

}
