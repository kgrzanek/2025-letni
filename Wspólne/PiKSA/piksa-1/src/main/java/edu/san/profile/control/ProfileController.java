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
    if (isEmailInUse(signUpCommand.getEmail()))
      return signUpResponseFactory.emailInUse();

    if (isPasswordWeak(signUpCommand.getPassword()))
      return signUpResponseFactory.passwordIsWeak();

    final var profileId = profileRepository.addProfile(
        signUpCommand.getEmail(),
        signUpCommand.getPassword(),
        signUpCommand.getFirstName().orElse(null),
        signUpCommand.getLastName().orElse(null));

    return signUpResponseFactory.success(profileId);
  }

  private static boolean isPasswordWeak(String password) {
    return password.length() < 5;
  }

  private boolean isEmailInUse(String email) {
    return profileRepository.findProfileIdByEmail(email).isPresent();
  }

}
