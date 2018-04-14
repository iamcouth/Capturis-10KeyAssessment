package com.capturis.tenkeyassessment.assessment.api;

import com.capturis.tenkeyassessment.assessment.data.DataAccess;
import com.capturis.tenkeyassessment.assessment.models.Assessment;

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

}
