package edu.san.profile.entity.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import edu.san.profile.control.ProfileId;
import edu.san.profile.entity.ProfileRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ProfileRepositoryImpl implements ProfileRepository {

  private final Map<ProfileIdImpl, ProfileImpl> profiles = new HashMap<>();

  private final Map<String, ProfileId> email2ProfileId = new HashMap<>();

  @Override
  public synchronized Optional<ProfileId> findProfileIdByEmail(String email) {
    return Optional.ofNullable(email2ProfileId.get(email));
  }

  @Override
  public synchronized ProfileId addProfile(String email, String password,
      String firstName,
      String lastName) {

    final var profileId = new ProfileIdImpl(UUID.randomUUID());
    final var profile = new ProfileImpl(profileId, email, password, firstName,
        lastName);

    profiles.put(profileId, profile);
    email2ProfileId.put(email, profileId);

    return profileId;
  }

}
