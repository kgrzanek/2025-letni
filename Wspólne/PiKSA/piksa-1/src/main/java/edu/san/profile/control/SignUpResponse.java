package edu.san.profile.control;

public interface SignUpResponse {

  interface Factory {

    SignUpResponse emailInUse();

    SignUpResponse passwordIsWeak();

    SignUpResponse profileAdded(ProfileId profileId);

  }

  public enum Result {
    CREATED, EMAIL_IN_USE, WEAK_PASSWORD
  }

}
