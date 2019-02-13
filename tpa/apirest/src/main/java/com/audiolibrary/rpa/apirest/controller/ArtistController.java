package com.audiolibrary.rpa.apirest.controller;

import com.audiolibrary.rpa.apirest.model.Artist;
import com.audiolibrary.rpa.apirest.repository.AlbumRepository;
import com.audiolibrary.rpa.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // GET /artists/1
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json",
            value = "/{id}")
    public Artist findById(@PathVariable(value = "id") Integer id){
        Artist artist = artistRepository.findOne(id);
        return artist;
    }

    @RequestMapping(value = "", params = {"name", "page", "size", "sortDirection", "sortProperty"})
    public Page<Artist> findByName(
            @RequestParam("name") String name,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sortDirection") String sortDirection,
            @RequestParam("sortProperty") String sortProperty) {
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findByNameContaining(name, pageRequest);
    }


        @RequestMapping(method = RequestMethod.GET,
        produces = "Application/json",
        value="", params = {"page", "size", "sortDirection", "sortProperty"})
    public Page<Artist> findAllPaging(
                @RequestParam("page") Integer page,
                @RequestParam("size") Integer size,
                @RequestParam("sortDirection") String sortDirection,
                @RequestParam("sortProperty") String sortProperty) {
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageRequest);
        }


    @RequestMapping(method = RequestMethod.POST,
    consumes = "Application/json",
    value="")
    public Artist createArtist(@RequestBody Artist artist) {
        String name = artist.getName();
        return artistRepository.save(artist);
    }

    @RequestMapping(method = RequestMethod.PUT,
    consumes = "Application/json",
    value = "/{id}")
    public Artist editArtist(@PathVariable Integer id, @RequestBody Artist artist) {
        String name = artist.getName();
        return artistRepository.save(artist);
    }

    @RequestMapping(method = RequestMethod.DELETE,
    value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id) {
        Artist artist = artistRepository.findOne(id);
        String name = artist.getName();
        artistRepository.delete(id);
    }

}
