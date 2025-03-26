package edu.san.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetIterationBody {

  void accept(ResultSet resultSet) throws SQLException;

}
