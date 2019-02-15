package com.audiolibrary.rpa.apirest.model;

import javax.persistence.*;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String title;

    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;


    public Album() {
    }

    public Album(Integer id, String title) {
        this.id = id;
        this.title = title;

    }

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}