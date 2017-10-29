package org.lee.java.model;

import java.util.List;

public class Album {
    private String musician; // 歌手
    private List<Track> trackList;  // 曲リスト

    public Album(String musician, List<Track> trackList) {
        this.musician = musician;
        this.trackList = trackList;
    }

    public String getMusician() {
        return musician;
    }

    public List<Track> getTrackList() {
        return trackList;
    }
}

