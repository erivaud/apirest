package com.audiolibrary.rpa.apirest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Album {

    //@ManyToOne
    //@JoinColumn(name = "artistId", insertable = false, updatable = false)
    //private Artist artist;
    // Repeated column in mapping for entity: com.myaudiolibrary.apirest.model.Album column: artistId (should be mapped with insert="false" update="false")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String title;
    public Integer artistId;

    public Album() {
    }

    public Album(Integer id, String title, Integer artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
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

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}