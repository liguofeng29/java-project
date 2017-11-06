package org.lee.java.aio.model;

public class Track {
    private String name;
    private long length;

    public Track(String name, long length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public long getLength() {
        return length;
    }
}
