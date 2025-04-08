package edu.san.jdbc;

import java.sql.Connection;

public interface Tx {

  Connection getConnection();

}
