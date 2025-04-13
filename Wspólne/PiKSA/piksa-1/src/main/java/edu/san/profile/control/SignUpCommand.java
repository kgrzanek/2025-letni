package edu.san.profile.control;

import java.util.Optional;

public interface SignUpCommand {

  String getEmail();

  String getPassword();

  Optional<String> getFirstName();

  Optional<String> getLastName();

}
