/**
 *
 * Backend LoginResource. Sends data utilizing the DAO.
 */

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

  /**
   * Used for getting User by UserId
   * @param id
   * @return UserLogin
   */
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

  /**
   * Used for getting all users
   * @return List of UserLogin
   */
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

  /**
   * Used to authenticate a user
   * @param userLogin
   * @return AssessmentUser
   */
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

  /**
   * Used to update an UserLogin object
   * @param userLogin
   * @return UserLogin
   */
  @PUT
  @Path("/{id}")
  public UserLogin update(UserLogin userLogin) {
    try {
      dataAccess.update(userLogin);
      return userLogin;
    } catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
    return null;
  }

  /**
   * Used to delete an UserLogin object
   * @param id
   */
  @DELETE
  @Path("/{id}")
  public void remove (@PathParam("id") int id) {
    try {
      dataAccess.remove(id);
    } catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
  }

}
