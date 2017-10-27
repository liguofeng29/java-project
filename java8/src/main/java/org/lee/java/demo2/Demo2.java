package org.lee.java.demo2;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.stream.Collectors;

import static java.lang.Character.isDigit;

/**
 * Stream API
 */
public class Demo2 {
    public static void main(String[] args) {

        // StreamAPIを使えば、操作の抽象度が高くなる・・らしい・・・

        demo1();

        demo2();

        demo3();

        demo4();

        demo5();

        demo7();

        demo8();
    }


    private static List<String> books = new ArrayList<String>() {
        {
            add("java");
            add("spring");
            add("xml");
        }
    };

    private static void demo1() {
        // 従来の書き方
        for (String book : books) {
            if (book.length() >= 4) {
                System.out.println("4文字超え書籍名:" + book);
            }
        }

        // 問題点
        // 1. 集合走査する度にテンプレートっぽいコードを書くことになる
        // 2. forを並列実行にリファクターするのは難しい、すべてのforが対象となる
        // 3. などなど

        books.stream()
                .filter(book -> book.length() >= 4)
                .forEach(book -> System.out.println("4文字超え書籍名:" + book));

    }

    // 中間操作と終端操作
    private static void demo2() {

        Stream.of("java", "spring", "c#")
                .filter(
                        book -> {
                            System.out.println("書籍名:" + book);
                            return book.length() >= 4;
                        });

        // 終端操作
        Stream.of("java", "spring", "c#")
                .filter(
                        book -> {
                            System.out.println("書籍名:" + book);
                            return book.length() >= 4;
                        })
                .count();

        // 判断する基準は？ 戻り値がStream -> 中間操作   それ以外終端操作？

    }

    private static void demo3() {
        List<String> list = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
    }

    // map
    private static void demo4() {
        List<String> uppers = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(uppers);
    }

    // filter
    private static void demo5() {
        List<String> beginningWithNumbers = Stream.of("1a1", "1abc", "abc1")
                .filter(value -> isDigit(value.charAt(0)))
                .collect(Collectors.toList());

        System.out.println(beginningWithNumbers);
    }


    // flatMap
    private static void demo6() {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
    }

    // max and min
    private static void demo7() {
        Integer max = Stream.of(1, 2, 5, 3, 4)
                .max(Comparator.comparing(num -> num.intValue()))
                .get();

        System.out.println("max is " + max);

        Integer min = Stream.of(1, 2, 5, 3, 4)
                .min(Comparator.comparing(num -> num.intValue()))
                .get();

        System.out.println("min is " + min);
    }

    // reduce
    private static void demo8() {
        int summary = Stream.of(1, 2, 3, 4)
                .reduce(0, (acc, element) -> acc + element);

        System.out.println("summary is " + summary);
    }





    // refactor
    private static void demo9()

    {

    }

}


