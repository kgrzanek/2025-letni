package edu.san.jdbc;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import edu.san.ex.Ex;

public final class JDBC {

  static final Logger LOG = System.getLogger(JDBC.class.getName());

  public static void withSerializationRestarts(
      IsSerializationFailure pred,
      int allowedRestartsCount,
      Runnable body) {

    if (allowedRestartsCount < 0)
      throw new IllegalArgumentException(
          "Negative allowedRestartsCount = " + allowedRestartsCount);

    while (true) {
      if (allowedRestartsCount == 0) {
        body.run();
        return;
      }
      try {
        body.run();
        return;
      } catch (final Exception e) {
        if (IsSerializationFailure.test(pred, e)) {
          allowedRestartsCount--;
        } else {
          Ex.raise(e);
        }
      }
    }
  }

  public static void withTx(
      Connection conn,
      IsolationLevel isolationLevel,
      JDBConsumer<Tx> body) throws SQLException {

    final var transactionIsolationOriginal = conn.getTransactionIsolation();
    final var autoCommitOriginal = conn.getAutoCommit();
    var isCommitted = false;

    try {
      conn.setTransactionIsolation(isolationLevel.value());
      conn.setAutoCommit(false);

      record TxImpl(Connection conn) implements Tx {
        @Override
        public Connection getConnection() {
          return conn;
        }
      }

      body.accept(new TxImpl(conn));
      conn.commit();
      isCommitted = true;
    } finally {
      if (isCommitted) {
        conn.setTransactionIsolation(transactionIsolationOriginal);
        conn.setAutoCommit(autoCommitOriginal);
      }
    }
  }

  public static void withTx(
      DataSource dataSource,
      IsolationLevel isolationLevel,
      JDBConsumer<Tx> body) {
    withConnection(dataSource, conn -> withTx(conn, isolationLevel, body));
  }

  public static void withConnection(
      DataSource dataSource,
      JDBConsumer<Connection> body) {
    try (var conn = dataSource.getConnection()) {
      body.accept(conn);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      Ex.raise(e);
    }
  }

  public static void withStatement(
      Connection conn,
      JDBConsumer<Statement> body) {
    try (var statement = conn.createStatement()) {
      body.accept(statement);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      Ex.raise(e);
    }
  }

  public static void withPreparedStatement(
      String sql,
      Connection conn,
      JDBConsumer<PreparedStatement> body) {
    try (var statement = conn.prepareStatement(sql)) {
      body.accept(statement);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      Ex.raise(e);
    }
  }

  public static void withPreparedStatement(
      String sql,
      DataSource dataSource,
      JDBConsumer<PreparedStatement> body) {
    withConnection(dataSource, conn -> withPreparedStatement(sql, conn, body));
  }

  public static int execUpdate(String sql, Statement statement) {
    try {
      return statement.executeUpdate(sql);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      return Ex.raise(e);
    }
  }

  public static int execUpdate(String sql, Connection conn) {
    final var counter = new Object() {
      int value;
    };
    withStatement(conn,
        statement -> counter.value = execUpdate(sql, statement));

    return counter.value;
  }

  public static void forEachQueryResult(
      String sql,
      Statement statement,
      JDBConsumer<ResultSet> body) {
    try (var rs = statement.executeQuery(sql)) {
      forEachResultSetRow(rs, body);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      Ex.raise(e);
    }
  }

  public static void forEachResultSetRow(
      ResultSet resultSet,
      JDBConsumer<ResultSet> body)
      throws SQLException {
    while (resultSet.next()) {
      body.accept(resultSet);
    }
  }

  public static void forEachResultSetRow(
      PreparedStatement stmt,
      JDBConsumer<ResultSet> body)
      throws SQLException {
    try (var rs = stmt.executeQuery()) {
      forEachResultSetRow(rs, body);
    }
  }

  public static void forEachResultSetRow(
      String sql,
      Connection conn,
      JDBConsumer<ResultSet> body) {
    withStatement(conn, stmt -> forEachQueryResult(sql, stmt, body));
  }

  public static void forEachResultSetRow(
      String sql,
      DataSource dataSource,
      JDBConsumer<ResultSet> body) {
    withConnection(dataSource, conn -> forEachResultSetRow(sql, conn, body));
  }

  private JDBC() {}

}
