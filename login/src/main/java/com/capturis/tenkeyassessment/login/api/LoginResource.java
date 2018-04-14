package com.capturis.tenkeyassessment.login.api;

import com.capturis.tenkeyassessment.login.data.DataAccess;
import com.capturis.tenkeyassessment.login.model.UserLogin;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;


@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("auth")
public class LoginResource {

private final DataAccess dataAccess;

@Inject
public LoginResource(DataAccess dataAccess) {
  this.dataAccess = dataAccess;
}

  @GET
  @Path("/{id}")
  public UserLogin getById(@PathParam("id") int id) {
  try{
    return dataAccess.getById(id);
  }
  catch (SQLException e)
  {
    System.out.println(e.getMessage());
  }
    return null;
  }

//  @GET
//  @Produces(MediaType.APPLICATION_JSON)
//  @Path("/login")
//  public Response login()
//  {
//    //JSONObject response = new JSONObject();
//    String password = "Hey";
//    String wrongPassword = "Hey";
//    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
//    boolean check = CheckIfMatches(wrongPassword, hashed);
//    DataAccess d = new DataAccess();
//    UserLogin u = new UserLogin(password, hashed, check);
//    d.AddUser(u);
//    JsonObject response = new JsonObject();
//    response.addProperty("Password", password);
//    response.addProperty("Hashed", hashed);
//    //String testString = String.format("Hashed Password: %s, Does it match: %s", hashed, check);
//    return Response.ok(response.toString()).build();
//    //return String.format("Hashed Password: %s, Does it match: %s",hashed , check);
//  }
//
//  public boolean CheckIfMatches(String plainPw, String hashedPw)
//  {
//    return BCrypt.checkpw(plainPw, hashedPw);
//  }



}
