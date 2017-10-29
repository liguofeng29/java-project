package org.lee.java.demo3;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.lee.java.demo2.refactor.Refactor;
import org.lee.java.model.Album;

import java.io.IOException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Demo3 {

    private static Logger logger = Logger.getLogger(Demo3.class);

    public static void main(String[] args) {

        demo1();

        demo2();

        // demo_boxing();

        demo3();

    }

    private static void demo1() {

        logger.debug("ログ出力 : " + 重い処理());

        if (logger.isDebugEnabled()) {
            logger.debug("ログ出力 : " + 重い処理());
        }

        debugWithLambda(() -> "ログ出力 : " + 重い処理());
    }


    private static String 重い処理() {
        return "重い処理です！";
    }

    private static void debugWithLambda(Supplier<String> message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message.get());
        }
    }


    private static void demo_boxing() {

        // アルバム、曲は10000000
        Album album = Refactor.makeAlbumList(1, 100000000).get(0);
        long start;

        start = System.currentTimeMillis();
        Long summary1 =
            album.getTrackList().stream()
                .map(track -> track.getLength())
                .reduce(0L, (acc, element) -> acc + element);

        System.out.println("総長さ　 : " + summary1);
        System.out.println("auto boxing 消費時間 : " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        Long summary2 =
            album.getTrackList().stream()
                .mapToLong(track -> track.getLength())
                .reduce(0, (acc, element) -> acc + element);

        System.out.println("総長さ　 : " + summary2);
        System.out.println("mapToLong消費時間 : " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        LongSummaryStatistics trackLengthStats =
            album.getTrackList().stream()
                .mapToLong(track -> track.getLength())
                .summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
            trackLengthStats.getMax(),
            trackLengthStats.getMin(),
            trackLengthStats.getAverage(),
            trackLengthStats.getSum());

        System.out.println("");
        System.out.println("summaryStatistics消費時間 : " + (System.currentTimeMillis() - start));

    }

    private static void demo2() {
        Parent parent = new ParentImpl();
        parent.hey();

        Child child = new ChildImpl();
        child.hey();
    }

    private static void demo3() {

        Optional op1 = Optional.of("value1");
        Optional op2 = Optional.empty();
        Optional op3 = Optional.ofNullable(null);


        assertTrue(op1.isPresent());
        assertFalse(op2.isPresent());
        assertFalse(op3.isPresent());


        assertEquals("value1", op1.get());
        // java.util.NoSuchElementException
        // assertEquals(null, op2.get());
        // assertEquals(null, op3.get());




        Optional op4 = Optional.empty();
        assertEquals("get else value", op4.orElse("get else value"));

        Optional op5 = Optional.ofNullable(null);
        assertEquals("get lambda value", op5.orElseGet(() -> "get lambda value"));

        Optional op6 = Optional.ofNullable(null);

        try {
            op5.orElseThrow(() -> new IOException("IO例外発生させる"));
        } catch (Throwable throwable) {
            assertEquals("IO例外発生させる",throwable.getMessage());
        }
    }
}

