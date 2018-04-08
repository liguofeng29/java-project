package org.lee.java.aio;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.lee.java.aio.model.Album;
import org.lee.java.aio.model.AlbumFactory;
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
public class CompletableFutureSample {

    private static List<Album> albums = AlbumFactory.get(2, 5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        lookupByName("album1");
        System.out.println("検索時間 : " + ((System.currentTimeMillis() - start) / 1000));
    }

    public static void lookupByName(String albumName) throws InterruptedException {

        CompletableFuture<List<String>> lookupTracks = loginTo("track")
            .thenCompose(auth -> lookupTracks(albumName));

        CompletableFuture<List<String>> lookupMusicians = loginTo("musician")
            .thenCompose(auth -> lookupMusicians(albumName));


        // 二つを結ぶ
        lookupTracks.thenCombine(lookupMusicians, (tracks, musicians) -> {
            System.out.println("曲リスト：" +
                tracks.stream()
                    .collect(Collectors.joining(", "))
            );
            System.out.println("ミュージシャン：" +
                musicians.stream()
                    .collect(Collectors.joining(", "))
            );
            return true;
        }).join(); // joinで実行
    }

    // ログイン処理
    private static CompletableFuture<Boolean> loginTo(String kind) {
        final int sleep;
        if (kind.equals("track")) {
            sleep = 5;
        } else {
            sleep = 10;
        }
        ExecutorService executor = Executors.newFixedThreadPool(2);
        return CompletableFuture.supplyAsync(
            () -> {
                System.out.println(sleep + "秒かかる" + kind + " login処理開始");
                try {
                    Thread.sleep(sleep * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(sleep + "秒かかる" + kind + " login処理終了");
                return true;
            });
    }

    // 曲検索
    private static CompletableFuture<List<String>> lookupTracks(String albumName) {
        return CompletableFuture.supplyAsync(
            () -> {
                System.out.println("曲検索開始");
                try {
                    Thread.sleep(1000 * 8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("曲検索終了");
                return Stream.of("曲1", "曲2", "曲3")
                    .collect(Collectors.<String>toList());
            }
        );
    }

    // ミュージシャン検索
    private static CompletableFuture<List<String>> lookupMusicians(String albumName) {
        return CompletableFuture.supplyAsync(
            () -> {
                System.out.println("ミュージシャン検索開始");
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ミュージシャン検索終了");
                return Stream.of("ミュージシャン1", "ミュージシャン1")
                    .collect(Collectors.<String>toList());
            }
        );
    }
}
