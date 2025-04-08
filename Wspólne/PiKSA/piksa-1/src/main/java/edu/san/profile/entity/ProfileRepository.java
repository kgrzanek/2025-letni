package edu.san.profile.entity;

import java.util.Optional;

import edu.san.profile.control.ProfileId;

public interface ProfileRepository {

  Optional<ProfileId> findProfileIdByEmail(String email);

  ProfileId addProfile(
      String email,
      String password,
      String firstName,
      String lastName);

}
