// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.greeting.boundary.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FirstNameValidator
    implements ConstraintValidator<ValidFirstName, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isBlank())
      return false;

    final var firstChar = value.charAt(0);
    return Character.isUpperCase(firstChar);
  }

}
