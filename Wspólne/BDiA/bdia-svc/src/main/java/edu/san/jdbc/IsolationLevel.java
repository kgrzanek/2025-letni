// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jdbc;

import java.sql.Connection;

public enum IsolationLevel {

  READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
  REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
  SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

  private final int value;

  IsolationLevel(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

}
