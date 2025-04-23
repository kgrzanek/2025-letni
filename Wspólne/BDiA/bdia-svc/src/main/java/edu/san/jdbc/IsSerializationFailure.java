package edu.san.jdbc;

import java.sql.SQLException;
import java.util.function.Predicate;

import org.postgresql.util.PSQLException;

@FunctionalInterface
public interface IsSerializationFailure extends Predicate<Throwable> {

  IsSerializationFailure inPostgres = e -> e instanceof final PSQLException p
      && "40001".equals(p.getSQLState());

  IsSerializationFailure inOracle = e -> e instanceof final SQLException p
      && p.getErrorCode() > 0;

  IsSerializationFailure inSqlServer = e -> e instanceof final SQLException p
      && p.getErrorCode() > 0;

  static boolean test(
      IsSerializationFailure pred,
      Throwable e) {
    if (pred.test(e))
      return true;

    final var cause = e.getCause();
    if (cause != null && pred.test(cause))
      return true;

    for (final Throwable s : e.getSuppressed()) {
      if (pred.test(s))
        return true;
    }

    return false;
  }

}
