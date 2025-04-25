package edu.san.greeting.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Objects;

import edu.san.greeting.control.GreetingController;
import edu.san.greeting.control.GreetingQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/greeting")
@ApplicationScoped
public class GreetingResource {

  private static final Logger LOG = System
      .getLogger(GreetingResource.class.getName());

  private final GreetingController greetingController;

  public GreetingResource(GreetingController greetingController) {
    this.greetingController = Objects.requireNonNull(greetingController);
  }

  public record GreetingQueryRequest(String whoToGreet)
      implements GreetingQuery {}

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response handlePost(GreetingQueryRequest request) {
    LOG.log(Level.DEBUG, "Działa metoda GreetingResource::hello()");
    return Response.ok(greetingController.sayHello(request))
        .build();
  }
}
