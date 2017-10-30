package org.lee.java.demo2.refactor;

import org.apache.commons.lang3.RandomStringUtils;
import org.lee.java.model.Album;
import org.lee.java.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Refactor {

    public static void main(String[] args) {

        // 要件 : 複数のアルバムから曲の長さが３分以上の曲名を取得する。

        // アルバムリスト生成
        List<Album> albumList = makeAlbumList(5,5);
        // 検索結果
        List<String> result = new ArrayList<>();
        // 1. アルバムリストを走査
        for (Album album : albumList) {
            // 2. 曲リストを走査
            for (Track track : album.getTrackList()) {
                // 3. 曲の長さ判定
                if (track.getLength() >= 3) {
                    // 4. 曲名保存
                    result.add(track.getName());
                }
            }
        }

        System.out.println(result);

        // 一回目リファクタ
        List<String> result1 = new ArrayList<>();
        albumList.stream()                                  // 1. StreamAPI取得
            .forEach(                                       // 2. 曲リストを走査
                album -> {
                    album.getTrackList().forEach(                // 3. 曲リストを走査
                        track -> {
                            if (track.getLength() >= 3) {        // 4. 曲長さ判定
                                result1.add(track.getName());    // 5. 曲名保存
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
                album.getTrackList().stream()
                    .filter(track -> track.getLength() >= 3)
                    .map(track -> track.getName())
                    .forEach(trackName -> result2.add(trackName));

            });

        // リファクター前後比較
        assertEquals(result, result2);

        // 三回目リファクタ　
        List<String> result3 = new ArrayList<>();

        albumList.stream()
            .flatMap(album -> album.getTrackList().stream())
            .filter(track -> track.getLength() >= 3)
            .map(track -> track.getName())
            .forEach(trackName -> result3.add(trackName));

        // リファクター前後比較
        assertEquals(result, result3);

        // 4回目 collect()
        List<String> result4 = albumList.stream()
            .flatMap(album -> album.getTrackList().stream())  // 1. 全ての曲
            .filter(track -> track.getLength() >= 3)          // 2. 曲長さで絞る
            .map(track -> track.getName())                    // 3. 名前だけほしい
            .collect(Collectors.toList());                    // 4. 結果はListにする
    }

    // アルバム生成
    public static List<Album> makeAlbumList(int albumSize, int trackSize) {

        List<Album> albumList = new ArrayList<>();

        for (int i = 0; i < albumSize; i++) {

            Album album = new Album("musician:" + RandomStringUtils.randomAlphabetic(10), makeTrackList(trackSize));
            albumList.add(album);
        }

        return albumList;
    }

    // 曲生成
    private static List<Track> makeTrackList(int trackSize) {

        List<Track> trackList = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < trackSize; i++) {

            Track track = new Track("track : " + RandomStringUtils.randomAlphabetic(10), random.nextInt(5) + 1);
            trackList.add(track);
        }

        return trackList;
    }

    // 曲名表示
    private static void show(List<Track> trackList) {
        for (Track track : trackList) {
            System.out.println(track.getName() + " : " + track.getLength());
        }
    }
}
