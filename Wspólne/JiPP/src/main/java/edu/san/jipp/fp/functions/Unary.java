// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.fp.functions;

@FunctionalInterface
public interface Unary<S, T> {

  T call(S arg);

}
