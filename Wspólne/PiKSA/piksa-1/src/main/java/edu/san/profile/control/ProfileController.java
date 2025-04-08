package edu.san.profile.control;

import edu.san.profile.entity.ProfileRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfileController {

  private final SignUpResponse.Factory signUpResponseFactory;

  private final ProfileRepository profileRepository;

  public ProfileController(
      SignUpResponse.Factory signUpResponseFactory,
      ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
    this.signUpResponseFactory = signUpResponseFactory;
  }

  public SignUpResponse signUp(SignUpCommand signUpCommand) {
    if (isEmailInUse(signUpCommand.email()))
      return signUpResponseFactory.emailInUse();

    if (isPasswordWeak(signUpCommand.password()))
      return signUpResponseFactory.passwordIsWeak();

    final var profileId = profileRepository.addProfile(
        signUpCommand.email(),
        signUpCommand.password(),
        signUpCommand.firstName().orElse(null),
        signUpCommand.lastName().orElse(null));

    return signUpResponseFactory.profileAdded(profileId);
  }

  private static boolean isPasswordWeak(String password) {
    return password.length() < 5;
  }

  private boolean isEmailInUse(String email) {
    return profileRepository.findProfileIdByEmail(email).isPresent();
  }

}
