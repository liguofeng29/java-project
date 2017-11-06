package org.lee.java.aio.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.lee.java.aio.model.Track;
import org.lee.java.aio.model.Album;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlbumFactory {


    private AlbumFactory() {
    }

    public static List<Album> get(int albumSize, int trackSize) {
        return makeAlbumList(albumSize, trackSize);
    }

    // アルバム生成
    private static List<Album> makeAlbumList(int albumSize, int trackSize) {

        List<Album> albumList = new ArrayList<>();

        for (int i = 0; i < albumSize; i++) {

            Album album = new Album(
                "album" + i,
                "musician:" + RandomStringUtils.randomAlphabetic(10),
                makeTrackList(trackSize));
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
}
