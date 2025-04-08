package edu.san.profile.control;

import java.util.Optional;

public interface SignUpCommand {

  String email();

  String password();

  Optional<String> firstName();

  Optional<String> lastName();

}
