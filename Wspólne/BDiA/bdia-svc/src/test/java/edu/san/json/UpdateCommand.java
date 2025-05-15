// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.json;

public sealed interface UpdateCommand {

  record UpdateEmail(long profileId, String email) implements UpdateCommand {}

  record UpdateAddress(long profileId, String city) implements UpdateCommand {}

}
