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

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;


    // GET /artists/1
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json",
            value = "/{id}")
    public Artist findById(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        Artist artist = artistRepository.findOne(id);
        if(artist == null){
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'a pas été trouvé");
        }
        return artist;
    }

    // GET /artists?name=aerosmith
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


        // GET /artists?page=0&size=10&sortProperty=name&sortDirection=ASC
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

    // 4 - Création d'un artiste
    // POST /artists avec les données de l'artiste en JSON dans le champ data de la requête.
    @RequestMapping(method = RequestMethod.POST,
            consumes = "Application/json",
            value = "")
    public Artist createArtist(@RequestBody Artist artist) throws EntityExistsException{
        String name = artist.getName();

        if(!artistRepository.findByName(name).isEmpty()) {
            throw new EntityExistsException("L'artiste nommé " + name + " existe déjà, oui oui, vous ne pouvez pas l'ajouter à nouveau !");
        }
        return artistRepository.save(artist);
    }

    // PUT /artists/4 avec les données de l'artiste en JSON dans le champ data de la requête
    @RequestMapping(method = RequestMethod.PUT,
            consumes = "Application/json",
            value = "/{id}")
    public Artist editArtist(@PathVariable Integer id, @RequestBody Artist artist) throws EntityNotFoundException {
        Artist a = artistRepository.findOne(id);
        String name = a.getName();
        if(artistRepository.findByName(name).isEmpty()){
            throw new EntityNotFoundException("L'artiste " + name +" que vous souhaitez modifier n'existe pas");
        }
        return artistRepository.save(artist);
    }

    // DELETE /artists/5
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id) throws EntityNotFoundException {
        Artist a = artistRepository.findOne(id);
        String name = a.getName();
        if(artistRepository.findByName(name).isEmpty()){
            throw new EntityNotFoundException("L'artiste " + name + " n'existe pas, vous ne pouvez pas le supprimer");
        }
        artistRepository.delete(id);
    }

}
