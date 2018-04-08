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

    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    private static List<Album> albums = AlbumFactory.get(2, 5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try {
            long start = System.currentTimeMillis();
            lookupByName("album1");
            System.out.println("検索時間 : " + ((System.currentTimeMillis() - start) / 1000));
        } finally {
            executor.shutdown();
        }
    }

    public static void lookupByName(String albumName) throws InterruptedException {

        Future<Boolean> trackLogin = loginTo("track");
        Future<Boolean> musicianLogin = loginTo("artist");

        try {
            Thread.sleep(1000);
            System.out.println("authTrack終わった？: " + trackLogin.isDone());
            boolean authTrack = trackLogin.get();
            System.out.println("authMusician終わった？: " + musicianLogin.isDone());
            boolean authMusician = musicianLogin.get();

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
