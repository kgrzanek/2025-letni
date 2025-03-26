package edu.san.sql;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

import javax.sql.DataSource;

import edu.san.ex.EX;

public final class JDBC {

  static final Logger LOG = System.getLogger(JDBC.class.getName());

  public static <T> T withConnection(
      DataSource dataSource,
      Function<Connection, T> body) {
    try (var conn = dataSource.getConnection()) {
      return body.apply(conn);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      return EX.raise(e);
    }
  }

  public static <T> T withStatement(
      Connection conn,
      Function<Statement, T> body) {
    try (var statement = conn.createStatement()) {
      return body.apply(statement);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      return EX.raise(e);
    }
  }

  public static void withResultSet(
      String sql,
      Statement statement,
      ResultSetIterationBody body) {
    try (var resultSet = statement.executeQuery(sql)) {
      while (resultSet.next()) {
        body.accept(resultSet);
      }
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
    }
  }

  public static void query(DataSource dataSource, String sql,
      ResultSetIterationBody body) {
    withConnection(dataSource, conn -> {
      query(conn, sql, body);
      return null;
    });
  }

  public static void query(Connection conn, String sql,
      ResultSetIterationBody body) {
    withStatement(conn, statement -> {
      withResultSet(sql, statement, body);
      return null;
    });
  }

  private JDBC() {}

}
