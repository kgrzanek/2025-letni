// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.greeting.boundary.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FirstNameValidator.class)
public @interface ValidFirstName {

  String message() default "Invalid FirstName";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
