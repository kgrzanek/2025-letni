package edu.san.greeting.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.san.sql.JDBC;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/hello")
class GreetingResource {

  static final Logger LOG = System
      .getLogger(GreetingResource.class.getName());

  static final String POSTGRES_URI = "jdbc:postgresql://localhost:5501/bdia?user=%s&password=%s&ssl=false";

  private final DataSource dataSource;

  GreetingResource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    return runSimpleConn4();
  }

  Response getMessages(Connection conn) {
    return JDBC.withStatement(conn, stmt -> {
      List<Long> messages = new ArrayList<>();
      JDBC.withResultSet("select * from bdia.test1", stmt, rs -> {
        messages.add(rs.getLong("id"));
      });
      return Response.ok(messages).build();
    });
  }

  Response runSimpleConn4() {
    return JDBC.withConnection(dataSource, this::getMessages);
  }

  Response runSimpleConn3() {
    // LOG.log(Level.INFO, "We try to establish a Postgres connection...");
    try (var conn = dataSource.getConnection()) {
      // LOG.log(Level.INFO, "Connection established: " + conn);

      //

      return Response.ok("Hello from Quarkus REST").build();
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  static Response runSimpleConn2() {
    LOG.log(Level.INFO, "We try to establish a Postgres connection...");
    try (var conn = DriverManager
        .getConnection(POSTGRES_URI.formatted("bdia_owner", "54321"))) {
      LOG.log(Level.INFO, "Connection established: " + conn);

      //

      return Response.ok("Hello from Quarkus REST").build();
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  static Response runSimpleConn1() {
    LOG.log(Level.INFO, "We try to establish a Postgres connection...");
    Connection conn = null;
    try {
      conn = DriverManager
          .getConnection(POSTGRES_URI.formatted("bdia_owner", "54321"));
      LOG.log(Level.INFO, "Connection established: " + conn);

      //

      return Response.ok("Hello from Quarkus REST").build();
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    } finally {
      if (conn != null) {
        try {
          conn.close();
          LOG.log(Level.INFO, "Connection closed");
        } catch (final SQLException e) {
          LOG.log(Level.ERROR, e);
        }
      }
    }
  }
}
