package com.challenge4.FilmApp.repository;

import com.challenge4.FilmApp.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface FilmRepo extends PagingAndSortingRepository<Film,String> {

    @Query(value = "select c from Film c WHERE c.filmCode = :code",nativeQuery = false)
    public Object getByFilmCode(@Param("code") String code);

    @Query(value = "select c from Film c WHERE c.id = :id",nativeQuery = false)
    public Film getById(@Param("id") Long code);

    @Query(value = "select c from Film c WHERE c.onShow = true",nativeQuery = false)
    public Page<Film> getOnShowFilm(Pageable pageable);
}
