package com.audiolibrary.rpa.apirest.controller;

import com.audiolibrary.rpa.apirest.model.Album;
import com.audiolibrary.rpa.apirest.model.Artist;
import com.audiolibrary.rpa.apirest.repository.AlbumRepository;
import com.audiolibrary.rpa.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // 7 POST /albums
    // POST /albums

    @RequestMapping(method = RequestMethod.POST,
    consumes = "Application/json",
    value = "")
    public Album addAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }

    @RequestMapping(method = RequestMethod.DELETE,
    value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) {
        albumRepository.delete(id);
    }
}
