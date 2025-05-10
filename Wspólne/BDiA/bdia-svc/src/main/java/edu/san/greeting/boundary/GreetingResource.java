package edu.san.greeting.boundary;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import edu.san.jdbc.IsSerializationFailure;
import edu.san.jdbc.IsolationLevel;
import edu.san.jdbc.JDBC;
import edu.san.jdbc.Tx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/hello")
@ApplicationScoped
class GreetingResource {

  private static final String POSTGRES_URI = "jdbc:postgresql://localhost:5501/bdia?user=%s&password=%s&ssl=false";

  private final Random rnd = new SecureRandom();

  private final DataSource dataSource;

  private final Logger log;

  GreetingResource(DataSource dataSource, Logger log) {
    this.dataSource = dataSource;
    this.log = log;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    return runSimpleConn6();
  }

  Response runSimpleConn6() {
    final List<String> messages = new ArrayList<>();
    JDBC.withSerializationRestarts(IsSerializationFailure.inPostgres, 3,
        () -> JDBC.withTx(dataSource, IsolationLevel.SERIALIZABLE,
            tx -> updateAndSelectMessages(tx, messages)));

    log.log(Level.INFO, "It worked! " + messages);
    return Response.ok(messages).build();
  }

  @SuppressWarnings("resource")
  private void updateAndSelectMessages(Tx tx,
      final List<String> resultingMessages) {
    final var newEmail = rnd.nextBoolean() ? "kgrzanek@san.edu.pl"
        : "kongra@gmail.com";
    JDBC.execUpdate("update bdia.test1 set email = '%s' where id=1"
        .formatted(newEmail), tx.getConnection());

    JDBC.forEachResultSetRow(
        "select cast(id as text), email from bdia.test1 where email='kgrzanek@san.edu.pl'",
        tx.getConnection(), rs -> {
          resultingMessages.add(rs.getObject("id", String.class));
          resultingMessages.add(rs.getObject("email", String.class));
        });
  }

  Response runSimpleConn5() {
    final List<String> messages = new ArrayList<>();
    JDBC.withPreparedStatement(
        "select cast(id as text), email from bdia.test1 where email=?",
        dataSource, stmt -> {
          stmt.setString(1, "kgrzanek@san.edu.pl");
          JDBC.forEachResultSetRow(stmt, rs -> {
            messages.add(rs.getObject("id", String.class));
            messages.add(rs.getObject("email", String.class));
          });
        });
    return Response.ok(messages).build();
  }

  Response runSimpleConn4() {
    final List<String> messages = new ArrayList<>();
    JDBC.forEachResultSetRow(
        "select cast(id as text), email from bdia.test1 where email='kgrzanek@san.edu.pl'",
        dataSource, rs -> {
          messages.add(rs.getObject("id", String.class));
          messages.add(rs.getObject("email", String.class));
        });
    return Response.ok(messages).build();
  }

  Response runSimpleConn3() {
    log.log(Level.INFO, "We try to establish a Postgres connection...");
    try (var conn = dataSource.getConnection()) {
      log.log(Level.INFO, "Connection established: " + conn);

      // Business logic goes here:

      return Response.ok("Hello from Quarkus REST").build();
    } catch (final SQLException e) {
      log.log(Level.ERROR, e);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  Response runSimpleConn2() {
    log.log(Level.INFO, "We try to establish a Postgres connection...");
    try (var conn = DriverManager
        .getConnection(POSTGRES_URI.formatted("bdia_owner", "54321"))) {
      log.log(Level.INFO, "Connection established: " + conn);

      // Business logic goes here:

      return Response.ok("Hello from Quarkus REST").build();
    } catch (final SQLException e) {
      log.log(Level.ERROR, e);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  Response runSimpleConn1() {
    log.log(Level.INFO, "We try to establish a Postgres connection...");
    Connection conn = null;
    try {
      conn = DriverManager
          .getConnection(POSTGRES_URI.formatted("bdia_owner", "54321"));
      log.log(Level.INFO, "Connection established: " + conn);

      // Business logic goes here:

      return Response.ok("Hello from Quarkus REST").build();
    } catch (final SQLException e) {
      log.log(Level.ERROR, e);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    } finally {
      if (conn != null) {
        try {
          conn.close();
          log.log(Level.INFO, "Connection closed");
        } catch (final SQLException e) {
          log.log(Level.ERROR, e);
        }
      }
    }
  }
}
