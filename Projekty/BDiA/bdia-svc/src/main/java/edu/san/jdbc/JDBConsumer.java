package edu.san.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface JDBConsumer<T> {

  void accept(T t) throws SQLException;

}
