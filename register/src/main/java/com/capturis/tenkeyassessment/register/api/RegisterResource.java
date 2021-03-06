/**
 *
 * Backend RegisterResource. Sends data utilizing the DAO.
 */

package com.capturis.tenkeyassessment.register.api;

import com.capturis.tenkeyassessment.register.data.DataAccess;
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
public class RegisterResource {

  private final DataAccess dataAccess;

  @Inject
  public RegisterResource(DataAccess dataAccess) {
    this.dataAccess = dataAccess;
  }

  /**
   * Used for getting an assessmentuser by UserId
   *
   * @param id
   * @return assessmentuser
   */
  @GET
  @Path("/getuser/{id}")
  public AssessmentUser getById(@PathParam("id") int id) {
    try {
      return dataAccess.getUserById(id);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Used for getting all assessmentusers
   *
   * @return List of assessmentUsers
   */
  @GET
  @Path("/all/registeredusers")
  public List<AssessmentUser> findAll() {
    try {
      return dataAccess.findAll();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Used for creating an AssessmentUser
   *
   * @param assessmentUser
   * @return assessmentUser
   */
  @POST
  @Path("/register")
  public AssessmentUser create(AssessmentUser assessmentUser) {
    try {
      return dataAccess.create(assessmentUser);
    } catch (SQLException | IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Used for updating an assessmentuser
   *
   * @param assessmentUser
   * @return assessmentuser
   */
  @PUT
  @Path("/{id}")
  public AssessmentUser update(AssessmentUser assessmentUser) {
    try {
      dataAccess.update(assessmentUser);
      return assessmentUser;
    } catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
    return null;
  }

  /**
   * Used for deleting an assessmentUser
   *
   * @param id
   */
  @DELETE
  @Path("/{id}")
  public void remove(@PathParam("id") int id) {
    try {
      dataAccess.remove(id);
    } catch (SQLException | IOException e) {
      System.out.println((e.getMessage()));
    }
  }
}
