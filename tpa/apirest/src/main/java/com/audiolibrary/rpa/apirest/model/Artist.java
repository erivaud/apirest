package com.audiolibrary.rpa.apirest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Artist {

    //@OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    //@JsonIgnoreProperties("artist")
    //private Set<Album> albums = new HashSet<>();
    //private List<Album> albums;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;



    public Artist() {
    }

    public Artist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
