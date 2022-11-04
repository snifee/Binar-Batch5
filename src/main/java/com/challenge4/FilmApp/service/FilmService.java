package com.challenge4.FilmApp.service;

import com.challenge4.FilmApp.entity.Film;

import java.util.Map;

public interface FilmService {
    public Map save(Film film);

    public Map update(Film film);

    public Map delete(Film film);

    public Map getOnShowFilm(int size, int page);
}
