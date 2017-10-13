package org.xi.quick.test.lambda.functionalinterface;

@FunctionalInterface
public interface Testable<T> {

    boolean test(T t);
}