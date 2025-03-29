package edu.san.jdbc;

import java.sql.Connection;
import java.util.Objects;

public final class Tx {

  private final Connection connection;

  Tx(Connection connection) {
    this.connection = Objects.requireNonNull(connection);
  }

  Connection getConnection() {
    return connection;
  }

}
