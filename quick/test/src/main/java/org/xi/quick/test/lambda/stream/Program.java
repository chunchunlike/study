package org.xi.quick.test.lambda.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        printSplitLine("customCollectorTest");
        customCollectorTest();

        printSplitLine("toMapTest");
        toMapTest();

        printSplitLine("groupByTest");
        groupByTest();

        printSplitLine("partitionTest");
        partitionTest();

        printSplitLine("selectTest");
        selectTest();

        printSplitLine();

        printSplitLine();

        printSplitLine();

        printSplitLine();

    }

    static void customCollectorTest() {

        List<User> userList = getUserList();

        Map<Integer, User> userMap =
                userList.stream()
                        .collect(new ToMapCollector());
        System.out.println(userMap);
    }

    static void toMapTest() {

        List<User> userList = getUserList();

        Map<Integer, User> userMap =
                userList.stream()
                        .collect(Collectors.toMap(user -> user.getId(), user -> user));
        System.out.println(userMap);
    }

    static void groupByTest() {

        List<User> userList = getUserList();

        Map<String, List<User>> userMap =
                userList.stream()
                        .collect(Collectors.groupingBy(user -> user.getSex()));
        System.out.println(userMap);

        Map<String, Long> userMap2 =
                userList.stream()
                        .collect(Collectors.groupingBy(user -> user.getSex(),
                                Collectors.counting()));
        System.out.println(userMap2);

        Map<String, Map<String, List<User>>> userMap3 =
                userList.stream()
                        .collect(Collectors.groupingBy(user -> user.getSex(),
                                Collectors.groupingBy(user -> {
                                    if (user.getWeight() > 80) return "超重";
                                    if (user.getWeight() > 60) return "正常";
                                    return "过轻";
                                })));
        System.out.println(userMap3);

        Map<String, User> userMap4 =
                userList.stream()
                        .collect(Collectors.groupingBy(user -> user.getSex(),
                                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(user -> user.getWeight())),
                                        Optional::get)));
        System.out.println(userMap4);

        Map<String, List<User>> userMap5 =
                userList.stream()
                        .collect(Collectors.groupingBy(user -> user.getSex()));
        userMap5.forEach((key, entry) -> {
            entry.sort(Comparator.comparing(user -> user.getWeight()));
        });
        System.out.println(userMap5);
    }

    static void partitionTest() {

        List<User> userList = getUserList();

        Map<Boolean, List<User>> userPartition =
                userList.stream()
                        .collect(Collectors.partitioningBy(user -> user.getSex().equals("男")));
        System.out.println(userPartition);

        Map<Boolean, Long> userPartition2 =
                userList.stream()
                        .collect(Collectors.partitioningBy(user -> user.getSex().equals("男"),
                                Collectors.counting()));
        System.out.println(userPartition2);
    }

    static List<User> getUserList() {

        List<User> userList = Arrays.asList(
                new User(1, "郗世豪", "男", 76.5),
                new User(2, "朱凡凡", "女", 50.5),
                new User(3, "胡永强", "男", 80.8),
                new User(4, "贺嘉良", "男", 80.5),
                new User(5, "张敏", "男", 56.5),
                new User(6, "曹青", "女", 53.5)
        );

        List<User> users = new ArrayList<User>() {{
            add(new User(1, "郗世豪", "男", 76.5));
            add(new User(2, "朱凡凡", "女", 50.5));
            add(new User(3, "胡永强", "男", 80.8));
            add(new User(4, "贺嘉良", "男", 80.5));
            add(new User(5, "张敏", "男", 56.5));
            add(new User(6, "曹青", "女", 53.5));
        }};

        return users;
    }

    class Program$1 extends ArrayList<User> {
        {
            add(new User(1, "郗世豪", "男", 76.5));
            add(new User(2, "朱凡凡", "女", 50.5));
        }
    }

    class Program$2 extends ArrayList<User> {
        {
            add(new User(1, "郗世豪", "男", 76.5));
            add(new User(2, "朱凡凡", "女", 50.5));
            add(new User(3, "胡永强", "男", 80.8));
        }
    }

    static void selectTest() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 5, 3);
        List<Integer> numbers2 = Arrays.asList(21, 42, 56, 32, 54, 625);
        List<List<Integer>> pairs =
                numbers1.stream()
                        .flatMap(i ->
                                numbers2.stream()
                                        .filter(j -> i * j % 5 == 0)
                                        .map(j -> Arrays.asList(i, j)))
                        .collect(Collectors.toList());

        System.out.println(pairs);
    }

    static void printSplitLine() {
        printSplitLine("");
    }

    static void printSplitLine(String s) {
        System.out.println();
        System.out.println("=================================" + s + "================================");
    }
}
