package org.lee.java.demo2.refactor;

import java.util.List;

public class Album {
    public String musician; // 歌手
    List<Track> trackList;  // 曲リスト

    public Album(String musician, List<Track> trackList) {
        this.musician = musician;
        this.trackList = trackList;
    }
}
