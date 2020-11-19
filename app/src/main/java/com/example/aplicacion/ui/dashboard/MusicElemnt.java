package com.example.aplicacion.ui.dashboard;

public class MusicElemnt {
    public String name;
    public String artist;
    public String path;
    public long id;

    public MusicElemnt(String name, String artist, long id) {
        this.name = name;
        this.artist = artist;
        this.id = id;
    }
    public MusicElemnt(){}

    public long getId() {
        return id;
    }

    public MusicElemnt(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
