package org.lee.java.demo6;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.lee.java.demo2.refactor.Refactor;
import org.lee.java.model.Album;
import org.lee.java.model.Track;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.lee.java.demo2.refactor.Refactor.makeAlbumList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class Demo6 {


    public static void main(String[] args) {

        // demo1();

        demo_mock();

//        allToUpperCase_test();
//
//        firstToUpperCase_test();

    }


    // debug with peek
    private static void demo1() {

        // アルバムリスト生成
        Album album = makeAlbumList(1, 5).get(0);

        // アルバムから3分以上の曲名リスト取得
        List<String> trackNameList1 = album.getTrackList().stream()
            .filter(track -> track.getLength() >= 3)
            .map(Track::getName)
            .collect(Collectors.<String>toList());

        // debug
        album.getTrackList().stream()
            .filter(track -> track.getLength() >= 3)
            .forEach(track -> System.out.println("曲名 : " + track.getName() + " 長さ : " + track.getLength()));
        //      .map(Track::getName)
        //      .collect(Collectors.<String>toList());



        album.getTrackList().stream()
            .filter(track -> track.getLength() >= 3)
            .map(Track::getName)
            .forEach(trackName -> System.out.println("曲名 : " + trackName));
        //      .collect(Collectors.<String>toList());


        // アルバムから3分以上の曲名リスト取得
        List<String> trackNameList2 = album.getTrackList().stream()
            .filter(track -> track.getLength() >= 3)
            .peek(track -> System.out.println("曲名 : " + track.getName() + " 長さ : " + track.getLength()))
            .map(Track::getName)
            .peek(trackName -> System.out.println("曲名 : " + trackName))
            .collect(Collectors.<String>toList());


        // log出力
        Logger logger = Logger.getLogger(Demo6.class);
        List<String> trackNameList3 = album.getTrackList().stream()
            .filter(track -> track.getLength() >= 3)
            .peek(track -> logger.debug("曲名 : " + track.getName() + " 長さ : " + track.getLength()))
            .map(Track::getName)
            .peek(trackName -> logger.debug("曲名 : " + trackName))
            .collect(Collectors.<String>toList());

    }


    private static void demo_mock() {
        List<String> list = mock(List.class);

        // 既存の書き方
        when(list.size()).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                System.out.println("既存：値を返却する前の処理");
                return 3;
            }
        });


        // ラムダ式
        when(list.size()).thenAnswer(invocationOnMock -> {
            System.out.println("ラムダ：値を返却する前の処理");
            return 3;
        });

        assertEquals(3, list.size());
    }


    private static List<String> allToUpperCase(List<String> words) {
        return words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
    }

    private static void allToUpperCase_test() {
        // ラムダ式は匿名なので、
        // 1. コードをコピーする
        // 2. ラムダ式をメソッド内に入れる、テストされるのは、そのメソッドであり、ラムダ式ではない

        List<String> input = Arrays.asList("AB", "Ab", "ab", "aB");
        List<String> result = allToUpperCase(input);
        assertEquals(Arrays.asList("AB", "AB", "AB", "AB"), result);
    }


    private static List<String> firstToUpperCase(List<String> words) {
        return words.stream()
            .map(word -> {
                    // ここifなしで、綺麗に書けないのかな？
                    if (StringUtils.isEmpty(word)) {
                        return word;
                    } else {
                        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
                    }
                })
            .collect(Collectors.toList());
    }

    private static void firstToUpperCase_test() {
        List<String> input = Arrays.asList("AB", "Ab", "ab", "aB", "");
        List<String> result = firstToUpperCase(input);
        assertEquals(Arrays.asList("AB", "Ab", "Ab", "AB", ""), result);
    }


}
