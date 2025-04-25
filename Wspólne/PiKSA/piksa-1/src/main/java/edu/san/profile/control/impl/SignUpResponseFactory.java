package edu.san.profile.control.impl;

import java.util.Objects;

import edu.san.profile.control.ProfileId;
import edu.san.profile.control.SignUpResponse;
import edu.san.profile.control.SignUpResponse.Result;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SignUpResponseFactory implements SignUpResponse.Factory {

  @RegisterForReflection
  public static record SuccessSignUpResponse(Result result, ProfileId profileId)
      implements SignUpResponse {

    public SuccessSignUpResponse {
      Objects.requireNonNull(result);
      Objects.requireNonNull(profileId);
    }
  }

  @Override
  public SignUpResponse success(ProfileId profileId) {
    return new SuccessSignUpResponse(Result.SUCCESS, profileId);
  }

  @RegisterForReflection
  public static record EmailInUseSignUpResponse(Result result)
      implements SignUpResponse {}

  @Override
  public SignUpResponse emailInUse() {
    return new EmailInUseSignUpResponse(Result.EMAIL_IN_USE);
  }

  @RegisterForReflection
  public static record PasswordIsWeakSignUpResponse(Result result)
      implements SignUpResponse {}

  @Override
  public SignUpResponse passwordIsWeak() {
    return new PasswordIsWeakSignUpResponse(Result.PASSWORD_IS_WEAK);
  }

}
