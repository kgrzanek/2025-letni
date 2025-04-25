package edu.san.profile.boundary;

import java.util.Optional;

import edu.san.profile.control.SignUpCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpCommandRequest(
    @NotNull
    @NotBlank String email,

    @NotNull
    @NotBlank String password,

    String firstName,
    String lastName) implements SignUpCommand {

  @Override
  public String getEmail() {
    return email();
  }

  @Override
  public String getPassword() {
    return password();
  }

  @Override
  public Optional<String> getFirstName() {
    return Optional.ofNullable(firstName());
  }

  @Override
  public Optional<String> getLastName() {
    return Optional.ofNullable(lastName());
  }

}
