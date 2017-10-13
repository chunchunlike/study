package org.xi.quick.test.lambda.functionalinterface;

public interface Returnable<T, R> {
    R getValue(T t);
}
