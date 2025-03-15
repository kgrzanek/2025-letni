package edu.san.greeting.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Map;

import edu.san.greeting.boundary.queries.GreetingQueryImpl;
import edu.san.greeting.control.GreetingController;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/greeting")
public class GreetingResource {

  static final Logger LOG = System.getLogger(GreetingResource.class.getName());

  final GreetingController greetingController;

  public GreetingResource(GreetingController greetingController) {
    this.greetingController = greetingController;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello(GreetingQueryImpl greetingQuery) {
    LOG.log(Level.INFO, "Działa metoda GreetingResource::hello()");
    final var message = greetingController.sayHello(greetingQuery);
    final var greeting = Map.of("greetingMessage", message);
    return Response.ok(greeting).build();
  }
}
