package edu.san.profile.control;

public interface SignUpResponse {

  Result result();

  interface Factory {

    SignUpResponse success(ProfileId profileId);

    SignUpResponse emailInUse();

    SignUpResponse passwordIsWeak();

  }

  public enum Result {
    SUCCESS, EMAIL_IN_USE, PASSWORD_IS_WEAK
  }

}
