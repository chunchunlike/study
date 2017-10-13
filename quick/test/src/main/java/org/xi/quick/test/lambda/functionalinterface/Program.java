package org.xi.quick.test.lambda.functionalinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        testableTest();
        System.out.println("==========================");
        returnableTest();
    }

    static void testableTest() {

        List<Integer> list = Arrays.asList(9, 15, 20, 9, 3, 100);

        List<Integer> result;

        result = filter(list, new Testable<Integer>() {
            @Override
            public boolean test(Integer i) {
                return i > 10;
            }
        });
        System.out.println(result);

        result = filter(list, i -> i > 8);
        System.out.println(result);
    }

    static void returnableTest() {

        List<Integer> list = Arrays.asList(9, 15, 20, 9, 3, 100);

        List<String> result;

        result = returnString(list, new Returnable<Integer, String>() {
            @Override
            public String getValue(Integer i) {
                return "string " + i;
            }
        });
        System.out.println(result);

        result = returnString(list, i -> "string is " + i);
        System.out.println(result);
    }


    static <T> List<T> filter(List<T> list, Testable<T> t) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (t.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    static <T, R> List<R> returnString(List<T> list, Returnable<T, R> r) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(r.getValue(item));
        }
        return result;
    }
}
