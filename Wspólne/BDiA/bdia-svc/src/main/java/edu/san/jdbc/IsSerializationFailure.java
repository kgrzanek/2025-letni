package edu.san.jdbc;

import java.sql.SQLException;
import java.util.function.Predicate;

@FunctionalInterface
public interface IsSerializationFailure extends Predicate<Throwable> {

  IsSerializationFailure inPostgres = e -> e instanceof final SQLException p
      && "40001".equals(p.getSQLState());

  IsSerializationFailure inOracle = e -> e instanceof final SQLException p
      && p.getErrorCode() == 8177;

  IsSerializationFailure inSqlServer = e -> e instanceof final SQLException p
      && (p.getErrorCode() == 3951 || p.getErrorCode() == 3952);

  static boolean test(
      IsSerializationFailure pred,
      Throwable e) {
    if (null == e)
      return false;

    if (pred.test(e) || test(pred, e.getCause()))
      return true;

    for (final Throwable supp : e.getSuppressed()) {
      if (test(pred, supp))
        return true;
    }

    return false;
  }

}
