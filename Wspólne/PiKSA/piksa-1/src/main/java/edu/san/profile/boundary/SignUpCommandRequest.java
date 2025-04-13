package edu.san.profile.boundary;

import java.util.Optional;

import edu.san.profile.control.SignUpCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpCommandRequest implements SignUpCommand {

  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;

  private String firstName;

  private String lastName;

  public SignUpCommandRequest() {}

  public SignUpCommandRequest(
      String email,
      String password,
      String firstName,
      String lastName) {

    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Optional<String> getFirstName() {
    return Optional.ofNullable(firstName);
  }

  @Override
  public Optional<String> getLastName() {
    return Optional.ofNullable(lastName);
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
