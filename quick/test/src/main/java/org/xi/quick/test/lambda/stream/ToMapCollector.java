package org.xi.quick.test.lambda.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToMapCollector implements Collector<User, Map<Integer, User>, Map<Integer, User>> {

    /**
     * A function that creates and returns a new mutable result container.
     * 建立新的结果容器
     *
     * @return a function which returns a new, mutable result container
     */
    @Override
    public Supplier<Map<Integer, User>> supplier() {
        //return () -> new HashMap<>();
        return HashMap::new;
    }

    /**
     * A function that folds a value into a mutable result container.
     * 将元素添加到结果容器
     *
     * @return a function which folds a value into a mutable result container
     */
    @Override
    public BiConsumer<Map<Integer, User>, User> accumulator() {
        return (map, user) -> map.put(user.getId(), user);
    }

    /**
     * A function that accepts two partial results and merges them.  The
     * combiner function may fold state from one argument into the other and
     * return that, or may return a new result container.
     * 合并两个结果容器
     *
     * @return a function which combines two partial results into a combined
     * result
     */
    @Override
    public BinaryOperator<Map<Integer, User>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    /**
     * Perform the final transformation from the intermediate accumulation type
     * 对结果容器应用最终转换
     *
     * @return a function which transforms the intermediate result to the final
     * result
     */
    @Override
    public Function<Map<Integer, User>, Map<Integer, User>> finisher() {
        return Function.identity();
    }

    /**
     * 定义收集器的行为
     *
     * @return an immutable set of collector characteristics
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
