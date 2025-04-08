package edu.san.jdbc;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import edu.san.ex.EX;

public final class JDBC {

  static final Logger LOG = System.getLogger(JDBC.class.getName());

  private static record TxImpl(
      Connection connection,
      int isolationLevel,
      boolean isAutoCommit) implements Tx, AutoCloseable {

    @Override
    public void close() throws Exception {
      connection.setTransactionIsolation(isolationLevel);
      connection.setAutoCommit(isAutoCommit);
    }

    @Override
    public Connection getConnection() {
      return connection;
    }
  }

  public static void withTx(
      Connection conn,
      int isolationLevel,
      JDBConsumer<Tx> body) throws Exception {

    try (var tx = new TxImpl(conn,
        conn.getTransactionIsolation(),
        conn.getAutoCommit())) {

      conn.setTransactionIsolation(isolationLevel);
      conn.setAutoCommit(false);

      body.accept(tx);

      conn.commit();
    }

  }

  public static void withConnection(
      DataSource dataSource,
      JDBConsumer<Connection> body) {
    try (var conn = dataSource.getConnection()) {
      body.accept(conn);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      EX.raise(e);
    }
  }

  public static void withStatement(
      Connection conn,
      JDBConsumer<Statement> body) {
    try (var statement = conn.createStatement()) {
      body.accept(statement);
    } catch (final SQLException e) {
      LOG.log(Level.ERROR, e);
      EX.raise(e);
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
      EX.raise(e);
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
      return EX.raise(e);
    }
  }

  public static int execUpdate(String sql, Connection conn) {
    final var counter = new Object() {
      int value;
    };
    withPreparedStatement(sql, conn, preparedStatement -> {
      counter.value = execUpdate(sql, preparedStatement);
    });

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

  public static void forResultSetRow(
      String sql,
      Connection conn,
      JDBConsumer<ResultSet> body) {
    withStatement(conn, stmt -> forEachQueryResult(sql, stmt, body));
  }

  public static void forEachResultSetRow(
      String sql,
      DataSource dataSource,
      JDBConsumer<ResultSet> body) {
    withConnection(dataSource, conn -> forResultSetRow(sql, conn, body));
  }

  private JDBC() {}

}
