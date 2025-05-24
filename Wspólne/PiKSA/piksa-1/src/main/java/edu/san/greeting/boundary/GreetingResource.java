package edu.san.greeting.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import edu.san.greeting.boundary.validation.ValidFirstName;
import edu.san.greeting.control.GreetingController;
import edu.san.greeting.control.GreetingQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/greeting")
@ApplicationScoped
public class GreetingResource {

  private final GreetingController greetingController;

  private final Logger log;

  public GreetingResource(GreetingController greetingController, Logger log) {
    this.greetingController = greetingController;
    this.log = log;
  }

  public record GreetingQueryRequest(
      @ValidFirstName String whoToGreet)
      implements GreetingQuery {}

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response handleGreet(@Valid GreetingQueryRequest request) {
    log.log(Level.INFO, "Działa metoda GreetingResource::handleGreet(request)");
    return Response.ok(greetingController.sayHello(request)).build();
  }
}
