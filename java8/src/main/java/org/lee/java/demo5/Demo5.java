package org.lee.java.demo5;

import org.lee.java.demo2.refactor.Refactor;
import org.lee.java.model.Album;
import org.lee.java.model.Track;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.summingDouble;


public class Demo5 {


    public static void main(String[] args) {

        // parallel
          demo1();

        // parallelDiceRolls(10);

        // demo3();

      //  demo4();

//         demo5();

    }


    // to value
    private static void demo1() {
        // アルバム
        Album album = Refactor.makeAlbumList(1, 10000000).get(0);

        long start = System.currentTimeMillis();

        long summary =
            album.getTrackList().stream()
                .mapToLong(Track::getLength)
                .sum();

        System.out.println("シングル処理時間: " + (System.currentTimeMillis() - start));
        System.out.println("総長さ: " + summary);

        start = System.currentTimeMillis();
        long summaryWithParallel =
            album.getTrackList().parallelStream()
                .mapToLong(Track::getLength)
                .sum();
        System.out.println("パラ処理時間: " + (System.currentTimeMillis() - start));
        System.out.println("総長さ: " + summaryWithParallel);

    }


    private static void parallelDiceRolls(int times) {
        double fraction = 1.0 / times;


        Map<Integer, Double> d =
            IntStream.range(0, times)                // 0からtimesまでの範囲
                .parallel()
                .mapToObj(time -> twoDiceThrows())   // サイコロ2個を投げた計
                .collect(
                    Collectors.groupingBy(
                        side -> side,                // 点数毎（２～１２）
                        Collectors.summingDouble(value -> 1)));
                        // Collectors.summingDouble(n -> fraction)));

        System.out.println(d);
    }


    private static int twoDiceThrows() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

    private int twoDiceThrows(ThreadLocalRandom random) {
        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

    private static void demo3() {
        int[] values = new int[10];

        int val = 10;
        Arrays.parallelSetAll(values, i -> i);

        System.out.println(
            Arrays.stream(values)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"))
        );
    }

    private static void demo4() {
        Integer[] sums = {1, 2, 3, 4};
        Arrays.parallelPrefix(sums, Integer::sum);

        System.out.println(
            Arrays.stream(sums)
                .mapToInt(Integer::intValue)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"))
        );
    }


    private static void demo5() {
        Integer[] sums = {4, 2, 1, 3};
        Arrays.parallelSort(sums);

        System.out.println(
            Arrays.stream(sums)
                .mapToInt(Integer::intValue)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"))
        );


    }
}
