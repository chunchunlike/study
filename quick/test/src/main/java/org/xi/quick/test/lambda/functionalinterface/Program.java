package org.xi.quick.test.lambda.functionalinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class Program {

    public static void main(String[] args) {
        System.out.println("==========================");
        testStep();
        System.out.println("==========================");
        testableTest();
        System.out.println("==========================");
        returnableTest();
        System.out.println("==========================");

    }

    static void testableTest() {

        List<Integer> list = Arrays.asList(9, 15, 20, 9, 3, 100);
        list.stream().collect(toList());
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

        result = returnValue(list, new Returnable<Integer, String>() {
            @Override
            public String getValue(Integer i) {
                return "string " + i;
            }
        });
        System.out.println(result);

        result = returnValue(list, i -> i.toString());
        System.out.println(result);

        //下一句无法运行
        //result = returnValue(list, Integer::toString);
        //System.out.println(result);


        List<String> list2 = Arrays.asList("hello", "work", "fuc");
        List<Integer> itemLenList;

        itemLenList = returnValue(list2, s -> s.length());
        System.out.println(itemLenList);

        itemLenList = returnValue(list2, String::length);
        System.out.println(itemLenList);
    }

    static void testStep() {
        Apple apple1 = new Apple("red", 10);
        Apple apple2 = new Apple("green", 80);
        Apple apple3 = new Apple("yellow", 40);
        Apple apple4 = new Apple("pink", 20);
        List<Apple> appleList = Arrays.asList(apple1, apple2, apple3, apple4);

        Predicate<Apple> applePredicate = apple -> apple.getColor().equals("green");

        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
        System.out.println(appleList);

        appleList.sort(((a1, a2) -> a2.getWeight().compareTo(a1.getWeight())));
        System.out.println(appleList);

        appleList.sort(Comparator.comparing(a -> a.getWeight()));
        System.out.println(appleList);

        appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(appleList);
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

    static <T, R> List<R> returnValue(List<T> list, Returnable<T, R> r) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(r.getValue(item));
        }
        return result;
    }
}
