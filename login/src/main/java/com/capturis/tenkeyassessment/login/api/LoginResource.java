package com.capturis.tenkeyassessment.login.api;

import com.capturis.tenkeyassessment.login.data.DataAccess;
import com.capturis.tenkeyassessment.login.model.UserLogin;
import com.capturis.tenkeyassessment.register.models.AssessmentUser;

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

  @GET
  @Path("/all/Users")
  public List<UserLogin> findAll() {
  try {
    return dataAccess.findAll();
  }
  catch (SQLException e) {
    System.out.println(e.getMessage());
  }
  return  null;
}

//  @POST
//  @Path("/post")
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//  public UserLogin create(UserLogin userLogin) {
//  try {
//    return dataAccess.login(userLogin);
//  }
//  catch (SQLException | IOException e) {
//    System.out.println(e.getMessage());
//  }
//  return null;
//}

  @POST
  @Path("/authenticate")
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

  public AssessmentUser login(UserLogin userLogin) {
  try {
    return dataAccess.authenticate(userLogin.getUsername(), userLogin.getPasswordHash());
  }
  catch (SQLException | IOException e) {
    System.out.println(e.getMessage());
  }
  return null;
  }

  @PUT
  @Path("/{id}")
  public UserLogin update(UserLogin userLogin) {
  try {
    dataAccess.update(userLogin);
    return userLogin;
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
