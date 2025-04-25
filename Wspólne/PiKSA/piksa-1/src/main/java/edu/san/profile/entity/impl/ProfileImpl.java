package edu.san.profile.entity.impl;

import java.util.Objects;

public record ProfileImpl(
    ProfileIdImpl profileId,
    String email,
    String password,
    @SuppressWarnings("unused") String firstName,
    @SuppressWarnings("unused") String lastName) {

  public ProfileImpl {
    Objects.requireNonNull(profileId);
    Objects.requireNonNull(email);
    Objects.requireNonNull(password);
  }

}
