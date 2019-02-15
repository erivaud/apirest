package com.audiolibrary.rpa.apirest.controller;

import com.audiolibrary.rpa.apirest.model.Album;
import com.audiolibrary.rpa.apirest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping(value = "/albums")
public class AlbumController {


    @Autowired
    private AlbumRepository albumRepository;


    @RequestMapping(method = RequestMethod.POST,
            consumes = "Application/json",
            produces = "Application/json",
            value = "")
    public Album addAlbum(@RequestBody Album album) throws EntityExistsException {
        /*Artist a = artistRepository.findOne(id);
        String name = a.getName();
        if(artistRepository.findByName(name).isEmpty()){
            throw new EntityNotFoundException("L'artiste " + name +" n'existe pas, vous ne pouvez pas lui ajouter d'album");
        }
        else*/
            if (!albumRepository.findByTitle(album.getTitle()).isEmpty()) {
            throw new EntityExistsException("L'album " + album.getTitle() +" existe déjà, vous ne pouvez pas l'ajouter à nouveau");
        }
        return albumRepository.save(album);
    }

    @RequestMapping(method = RequestMethod.DELETE,
    value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) throws EntityNotFoundException {
        Album a = albumRepository.findOne(id);
        if(null == a){
            throw new EntityNotFoundException("L'album " + a.getTitle() + " n'existe pas, vous ne pouvez le supprimer");
        }
        else if (null == a.getArtist()){
            throw new EntityNotFoundException("L'artiste de l'album " + a.getTitle() + " n'existe pas, vous ne pouvez pas supprimer l'album");
        }
        albumRepository.delete(id);
    }
}
