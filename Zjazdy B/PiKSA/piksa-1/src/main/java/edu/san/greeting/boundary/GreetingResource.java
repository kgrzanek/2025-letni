package edu.san.greeting.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import edu.san.greeting.control.GreetingController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/greeting")
public class GreetingResource {

  static final Logger LOG = System.getLogger(GreetingResource.class.getName());

  final GreetingController greetingController;

  public GreetingResource(GreetingController greetingController) {
    this.greetingController = greetingController;
  }

  @GET
  public Response handleGet(String greetingQuery) {
    return Response.ok(greetingQuery).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response handlePost(GreetingQueryImpl greetingQuery) {
    LOG.log(Level.DEBUG, "Działa metoda GreetingResource::hello()");
    final var greetingMessage = greetingController.sayHello(greetingQuery);
    return Response.ok(new GreetingResponse(greetingMessage)).build();
  }
}
