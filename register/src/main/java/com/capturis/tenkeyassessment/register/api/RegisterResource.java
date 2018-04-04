package com.capturis.tenkeyassessment.register.api;

import com.capturis.tenkeyassessment.register.data.DataAccess;
import com.capturis.tenkeyassessment.register.models.AssessmentUser;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/auth")
public class RegisterResource {

  DataAccess d = new DataAccess();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/register")
  public String register(AssessmentUser params)
  {
    d.AddUser(params);
    return String.format("Got creds - EMAIL: %s, PASSWORD: %s", params.getFirstName(), params.getLastName());
  }


}
