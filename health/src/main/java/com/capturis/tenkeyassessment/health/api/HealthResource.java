package com.capturis.tenkeyassessment.health.api;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
   * Simple Class to show us receiving HTTP requests.
   */
  @Singleton
  @Path("nice")
  public class HealthResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ping")
    public String ping() {
      return "Lorem Ipsum!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/fail")
    public String fail() {
      return "Failure";
    }
  }
