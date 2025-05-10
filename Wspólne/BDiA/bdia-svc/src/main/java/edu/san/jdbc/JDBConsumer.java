package edu.san.jdbc;

import java.sql.SQLException;
import java.util.Objects;

@FunctionalInterface
public interface JDBConsumer<T> {

  void accept(T t) throws SQLException;

  default JDBConsumer<T> andThen(JDBConsumer<? super T> after) {
    Objects.requireNonNull(after);
    return (var t) -> {
      accept(t);
      after.accept(t);
    };
  }

}
