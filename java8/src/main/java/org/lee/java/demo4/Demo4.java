package org.lee.java.demo4;


import org.lee.java.demo2.refactor.Refactor;
import org.lee.java.model.Album;
import org.lee.java.model.Track;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * collector
 */
public class Demo4 {


    public static void main(String[] args) {

        // to Collection
        demo1();

        // to value
        demo2();

        // partition
        demo3();

        // grouping
        demo4();

        // get String
        demo5();

        // 組み合わせ
        demo6();
    }

    // to other collection
    private static void demo1() {
        // アルバム
        Album album = Refactor.makeAlbumList(1, 5).get(0);

        // toList
        List<String> trackNameList = album.getTrackList()
            .stream()
            .map(Track::getName)
            .collect(Collectors.toList());
        System.out.println("Listへ : " + trackNameList);

        // toSet
        Set<String> trackNameSet = album.getTrackList()
            .stream()
            .map(Track::getName)
            .collect(Collectors.toSet());
        System.out.println("Setへ : " + trackNameSet);

        // toTreeSet
        TreeSet<String> trackNameTreeSet = album.getTrackList()
            .stream()
            .map(Track::getName)
            .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("TreeSetへ : " + trackNameTreeSet);
    }


    // to value
    private static void demo2() {
        // アルバム
        Album album = Refactor.makeAlbumList(1, 10).get(0);

        // もっとも長い曲を取得する
        Optional<Track> longest =
            album.getTrackList().stream()
                // Stream#maxを使えとIDEAが教えるけど・・
                .collect(Collectors.maxBy(Comparator.comparing(Track::getLength)));

        System.out.println("最大長さ曲名: " + longest.get().getName());
        System.out.println("長さ:" + longest.get().getLength());

        // 平均取得
        Double average =
            album.getTrackList().stream()
                .collect(Collectors.averagingLong(Track::getLength));

        System.out.println("平均長さ:" + average);
    }

    // to part
    private static void demo3() {
        // アルバム
        Album album = Refactor.makeAlbumList(1, 10).get(0);

        // 3分を基準に分割
        Map<Boolean, List<Track>> partition =
            album.getTrackList().stream()
                .collect(Collectors.partitioningBy(track -> track.getLength() >= 3));

        System.out.println("3分以上:");
        partition.get(Boolean.TRUE).stream()
            .forEach(track -> System.out.println("曲名: " + track.getName() + " 長さ:" + track.getLength()));

        System.out.println("3分未満:");
        partition.get(Boolean.FALSE).stream()
            .forEach(track -> System.out.println("曲名: " + track.getName() + " 長さ:" + track.getLength()));

    }


    // grouping
    private static void demo4() {
        // アルバム
        Album album = Refactor.makeAlbumList(1, 10).get(0);

        Map<Long, List<Track>> grouping =
            album.getTrackList().stream()
                .collect(Collectors.groupingBy(Track::getLength)); // 長さでグルーピングする

        // 長さ毎の曲数を取得する
        Map<Long, Integer> numberOfTrack = new HashMap<>();
        for (Map.Entry<Long, List<Track>> entry : grouping.entrySet()) {

            numberOfTrack.put(entry.getKey(), entry.getValue().size());
        }

        System.out.println(numberOfTrack);
    }

    // grouping
    private static void demo6() {
        // アルバム
        Album album = Refactor.makeAlbumList(1, 10).get(0);

        Map<Long, Long> grouping =
            album.getTrackList().stream()
                .collect(
                    Collectors.groupingBy(
                        Track::getLength,         // 長さでgrouping
                        Collectors.counting()     // groupingした結果に対して、countする
                    )); // 長さでグルーピングする

        System.out.println(grouping);
    }

    // to charters
    private static void demo5() {
        Album album = Refactor.makeAlbumList(1, 5).get(0);
        String result =
            album.getTrackList().stream()
                .map(Track::getName)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(result);
    }
}

