package org.xi.quick.test.lambda.functionalinterface;

@FunctionalInterface
public interface Returnable<T, R> {
    R getValue(T t);
}
