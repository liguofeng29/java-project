package org.lee.java.demo2.refactor;

import org.apache.commons.lang3.RandomStringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Main {

    public static void main(String[] args) {

        // 要件 : 複数のアルバムから曲の長さが３分以上の曲名を取得する。

        // アルバムリスト生成
        List<Album> albumList = makeAlbumList();
        // 検索結果
        List<String> result = new ArrayList<>();
        // 1. アルバムリストを走査
        for (Album album : albumList) {
            // 2. 曲リストを走査
            for (Track track : album.trackList) {
                // 3. 曲の長さ判定
                if (track.length >= 3) {
                    // 4. 曲名保存
                    result.add(track.name);
                }
            }
        }

        System.out.println(result);

        // 一回目リファクタ
        List<String> result1 = new ArrayList<>();
        albumList.stream()                                  // 1. StreamAPI取得
            .forEach(                                       // 2. 曲リストを走査
                album -> {
                    album.trackList.forEach(                // 3. 曲リストを走査
                        track -> {
                            if (track.length >= 3) {        // 4. 曲長さ判定
                                result1.add(track.name);    // 5. 曲名保存
                            }
                        }
                    );
                }
            );

        // リファクター前後比較
        assertEquals(result, result1);

        // 二回目リファクタ　
        List<String> result2 = new ArrayList<>();

        albumList.stream()
            .forEach(album -> {
                album.trackList.stream()
                    .filter(track -> track.length >= 3)
                    .map(track -> track.name)
                    .forEach(trackName -> result2.add(trackName));

            });

        // リファクター前後比較
        assertEquals(result, result2);

        // 三回目リファクタ　
        List<String> result3 = new ArrayList<>();

        albumList.stream()
            .flatMap(album -> album.trackList.stream())
            .filter(track -> track.length >= 3)
            .map(track -> track.name)
            .forEach(trackName -> result3.add(trackName));

        // リファクター前後比較
        assertEquals(result, result3);

        // 4回目 collect()
        List<String> result4 = albumList.stream()
            .flatMap(album -> album.trackList.stream())  // 1. 全ての曲
            .filter(track -> track.length >= 3)          // 2. 曲長さで絞る
            .map(track -> track.name)                    // 3. 名前だけほしい
            .collect(Collectors.toList());               // 4. 結果はListにする
    }

    // アルバム生成
    private static List<Album> makeAlbumList() {

        List<Album> albumList = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());

        int count = random.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {

            Album album = new Album("musician:" + RandomStringUtils.randomAlphabetic(10), makeTrackList());
            albumList.add(album);
        }

        return albumList;
    }

    // 曲生成
    private static List<Track> makeTrackList() {

        List<Track> trackList = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());

        int count = random.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {

            Track track = new Track("track : " + RandomStringUtils.randomAlphabetic(10), random.nextInt(5) + 1);
            trackList.add(track);
        }

        return trackList;
    }

    // 曲名表示
    private static void show(List<Track> trackList) {
        for (Track track : trackList) {
            System.out.println(track.name + " : " + track.length);
        }
    }
}
