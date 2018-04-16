package com.capturis.tenkeyassessment.assessment.api;

import com.capturis.tenkeyassessment.assessment.data.DataAccess;
import com.capturis.tenkeyassessment.assessment.models.Assessment;

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
