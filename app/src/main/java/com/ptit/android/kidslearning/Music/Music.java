package com.ptit.android.kidslearning.Music;

public class Music {
    private String id, name, lyrics, categoryID;

    public Music(String id, String name, String lyrics, String categoryID) {
        this.id = id;
        this.name = name;
        this.lyrics = lyrics;
        this.categoryID = categoryID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}
