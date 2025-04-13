package edu.san.profile.entity.impl;

import java.util.Objects;
import java.util.UUID;

import edu.san.profile.control.ProfileId;

public record ProfileIdImpl(UUID uuid) implements ProfileId {

  public ProfileIdImpl {
    Objects.requireNonNull(uuid);
  }

}
