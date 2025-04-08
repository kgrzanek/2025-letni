package edu.san.profile.entity.impl;

import java.util.Objects;

record ProfileImpl(
    ProfileIdImpl profileId,
    String email,
    String password,
    String firstName,
    String lastName) {

  ProfileImpl {
    Objects.requireNonNull(profileId);
    Objects.requireNonNull(email);
    Objects.requireNonNull(password);
  }

}
