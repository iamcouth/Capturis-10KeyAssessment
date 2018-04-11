package com.capturis.tenkeyassessment.login.api;

import com.capturis.tenkeyassessment.login.data.DataAccess;
import com.capturis.tenkeyassessment.login.model.UserLogin;
import com.google.gson.JsonObject;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Singleton
@Path("auth")
public class LoginResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/login")
  public Response login()
  {
    //JSONObject response = new JSONObject();
    String password = "Hey";
    String wrongPassword = "Hey";
    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    boolean check = CheckIfMatches(wrongPassword, hashed);
    DataAccess d = new DataAccess();
    UserLogin u = new UserLogin(password, hashed, check);
    d.AddUser(u);
    JsonObject response = new JsonObject();
    response.addProperty("Password", password);
    response.addProperty("Hashed", hashed);

    //String testString = String.format("Hashed Password: %s, Does it match: %s", hashed, check);
    return Response.ok(response).build();
    //return String.format("Hashed Password: %s, Does it match: %s",hashed , check);
  }

  public boolean CheckIfMatches(String plainPw, String hashedPw)
  {
    return BCrypt.checkpw(plainPw, hashedPw);
  }



}
