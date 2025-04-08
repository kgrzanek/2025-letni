package edu.san.profile.entity.impl;

import java.util.Objects;
import java.util.UUID;

import edu.san.profile.control.ProfileId;

record ProfileIdImpl(UUID uuid) implements ProfileId {

  ProfileIdImpl {
    Objects.requireNonNull(uuid);
  }

}
