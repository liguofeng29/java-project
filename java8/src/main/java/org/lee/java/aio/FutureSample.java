package org.lee.java.aio;

import org.lee.java.aio.model.AlbumFactory;
import org.lee.java.aio.model.Album;
import org.lee.java.aio.model.Track;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * アルバム名からミュージシャンとトラックリストを取得する
 * <p>
 * ミュージシャン取得とトラックリスト取得はそれぞれ権限が必要
 */
public class FutureSample {

    private static List<Album> albums = AlbumFactory.get(2, 5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        lookupByName("album1");
        System.out.println("検索時間 : " + ((System.currentTimeMillis() - start) / 1000));

    }

    public static void lookupByName(String albumName) throws InterruptedException {

        System.out.println("1. track login future生成(実行はしない)");
        Future<Boolean> trackLogin = loginTo("track");
        System.out.println("2. musician login future生成(実行はしない)");
        Future<Boolean> musicianLogin = loginTo("artist");
        try {
            boolean authTrack = trackLogin.get();
            boolean authMusian = musicianLogin.get();

            Future<List<String>> tracks = lookupTracks(albumName);
            Future<List<String>> musicians = lookupMusicians(albumName);

            System.out.println("曲リスト：" +
                tracks.get().stream()
                    .collect(Collectors.joining(", "))
            );
            System.out.println("ミュージシャン：" +
                musicians.get().stream()
                    .collect(Collectors.joining(", "))
            );
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

    // ログイン処理
    private static Future<Boolean> loginTo(String kind) {
        final int sleep;
        if (kind.equals("track")) {
            sleep = 5;
        } else {
            sleep = 10;
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        return executor.submit(
            () -> {
                System.out.println(sleep + "秒かかる" + kind + " login処理開始");
                Thread.sleep(sleep * 1000);
                System.out.println(sleep + "秒かかる" + kind + " login処理終了");
                return true;
            });
    }

    // 曲検索
    private static Future<List<String>> lookupTracks(String albumName) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        return (Future<List<String>>) executor.submit(
            () -> {
                System.out.println("曲検索開始");
                Thread.sleep(1000 * 8);
                System.out.println("曲検索終了");
                return Stream.of("曲1", "曲2", "曲3")
                    .collect(Collectors.<String>toList());
            }
        );
    }

    // ミュージシャン検索
    private static Future<List<String>> lookupMusicians(String albumName) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        return (Future<List<String>>) executor.submit(
            () -> {
                System.out.println("ミュージシャン検索開始");
                Thread.sleep(1000 * 2);
                System.out.println("ミュージシャン検索終了");
                return Stream.of("ミュージシャン1", "ミュージシャン1")
                    .collect(Collectors.<String>toList());
            }
        );
    }
}
