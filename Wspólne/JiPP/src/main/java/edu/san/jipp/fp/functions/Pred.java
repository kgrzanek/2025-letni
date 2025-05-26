// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.fp.functions;

@FunctionalInterface
public interface Pred<T> {

  boolean call(T arg);

}
