package org.xi.quick.codebuilder.functionalinterface;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/29 15:41
 */
@FunctionalInterface
public interface BinaryConsumer<T, S> {
    void accept(T t, S s);
}

