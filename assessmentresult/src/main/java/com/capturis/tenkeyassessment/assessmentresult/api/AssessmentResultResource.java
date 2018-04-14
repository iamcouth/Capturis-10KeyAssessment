package com.capturis.tenkeyassessment.assessmentresult.api;

import com.capturis.tenkeyassessment.assessmentresult.data.DataAccess;
import com.capturis.tenkeyassessment.assessmentresult.model.AssessmentResult;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

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


}
