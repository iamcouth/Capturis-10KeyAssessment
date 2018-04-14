package com.capturis.tenkeyassessment.register.api;

import com.capturis.tenkeyassessment.register.data.DataAccess;
import com.capturis.tenkeyassessment.register.models.AssessmentUser;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("auth")
public class RegisterResource {

  private final DataAccess dataAccess;

  @Inject
  public RegisterResource (DataAccess dataAccess) {this.dataAccess = dataAccess;}

  @GET
  @Path("/register/{id}")
  public AssessmentUser getById(@PathParam("id") int id) {
    try{
      return dataAccess.getUserById(id);
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
    return null;
  }

//  @POST
//  @Consumes(MediaType.APPLICATION_JSON)
//  @Produces(MediaType.APPLICATION_JSON)
//  @Path("/register/{id}")
//  public String register(AssessmentUser params)
//  {
//    d.AddUser(params);
//    return String.format("Got creds - EMAIL: %s, PASSWORD: %s", params.getFirstName(), params.getLastName());
//  }

}
