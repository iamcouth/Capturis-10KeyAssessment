package com.capturis.tenkeyassessment.assessmentresult.api;

import com.capturis.tenkeyassessment.assessmentresult.data.DataAccess;
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
@Path("assessmentresult")
public class AssessmentResultResource {

  private final DataAccess dataAccess;

  @Inject
  public AssessmentResultResource (DataAccess dataAccess) { this.dataAccess = dataAccess; }

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

  @GET
  @Path("/all/assessmentresults")
  public List<AssessmentResult> findAll() {
    try {
      return dataAccess.findAll();
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return  null;
  }

  @POST
  public AssessmentResult create(AssessmentResult assessmentResult) {
    try {
      return dataAccess.create(assessmentResult);
    }
    catch (SQLException | IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

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

}
