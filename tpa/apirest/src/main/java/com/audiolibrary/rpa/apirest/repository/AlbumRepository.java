package com.audiolibrary.rpa.apirest.repository;

import com.audiolibrary.rpa.apirest.model.Album;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Integer> {

    List<Album> findByTitle(String title);
}
