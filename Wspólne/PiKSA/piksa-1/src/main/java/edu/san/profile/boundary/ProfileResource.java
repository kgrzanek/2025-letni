package edu.san.profile.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import edu.san.profile.control.ProfileController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/profile")
public class ProfileResource {

  private static final Logger LOG = System
      .getLogger(ProfileResource.class.getName());

  private final ProfileController profileController;

  public ProfileResource(ProfileController profileController) {
    this.profileController = profileController;
  }

  @Path("/sign-up")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response handleSignUp(@Valid SignUpCommandRequest request) {
    LOG.log(Level.DEBUG, "Działa metoda ProfileResource::handleSignUp()");
    return Response.ok(profileController.signUp(request))
        .build();
  }
}
