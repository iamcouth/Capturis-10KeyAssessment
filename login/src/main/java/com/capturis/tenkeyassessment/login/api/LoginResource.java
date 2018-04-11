package com.capturis.tenkeyassessment.login.api;

import com.capturis.tenkeyassessment.login.data.DataAccess;
import com.capturis.tenkeyassessment.login.model.UserLogin;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("auth")
public class LoginResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/login")
  public String login()
  {
    String password = "Hey";
    String wrongPassword = "Hey";
    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    boolean check = CheckIfMatches(wrongPassword, hashed);
    DataAccess d = new DataAccess();
    UserLogin u = new UserLogin(password, hashed, check);
    d.AddUser(u);
    return String.format("Hashed Password: %s, Does it match: %s",hashed , check);
  }

  public boolean CheckIfMatches(String plainPw, String hashedPw)
  {
    return BCrypt.checkpw(plainPw, hashedPw);
  }



}
