package edu.san.greeting.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Objects;

import edu.san.greeting.control.GreetingController;
import edu.san.greeting.control.GreetingQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  public record GreetingQueryRequest(@NotNull @NotBlank String whoToGreet)
      implements GreetingQuery {
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response handleGreet(@Valid GreetingQueryRequest request) {
    LOG.log(Level.DEBUG, "Działa metoda GreetingResource::hello()");
    return Response.ok(greetingController.sayHello(request)).build();
  }
}
