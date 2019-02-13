package com.audiolibrary.rpa.apirest.repository;

import com.audiolibrary.rpa.apirest.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

    List<Artist>findByName(String name);

    Page<Artist> findByNameContaining(String name, Pageable pageable);
}
