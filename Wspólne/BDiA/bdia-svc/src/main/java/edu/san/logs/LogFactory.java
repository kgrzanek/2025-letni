// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.logs;

import java.lang.System.Logger;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

public class LogFactory {

  @SuppressWarnings("static-method")
  @Produces
  public Logger create(InjectionPoint injectionPoint) {
    final var clazz = injectionPoint.getMember().getDeclaringClass();
    return System.getLogger(clazz.getName());
  }

}
