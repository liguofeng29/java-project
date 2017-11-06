package org.lee.java.aio.model;

import java.util.List;

public class Album {
    private String albumName;
    private String musician;
    private List<Track> trackList;

    public Album(String albumName, String musician, List<Track> trackList) {
        this.albumName = albumName;
        this.musician = musician;
        this.trackList = trackList;
    }

    public String getMusician() {
        return musician;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public String getAlbumName() {
        return albumName;
    }
}

