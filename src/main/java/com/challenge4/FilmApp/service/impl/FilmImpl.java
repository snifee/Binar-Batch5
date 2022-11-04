package com.challenge4.FilmApp.service.impl;

import com.challenge4.FilmApp.entity.Film;
import com.challenge4.FilmApp.repository.FilmRepo;
import com.challenge4.FilmApp.service.FilmService;
import com.challenge4.FilmApp.utils.Config;
import com.challenge4.FilmApp.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FilmImpl implements FilmService {
    private static Logger logger = LoggerFactory.getLogger(FilmImpl.class);

    @Autowired
    public FilmRepo filmRepo;

    @Autowired
    public Response response;


    @Override
    public Map save(Film film) {
        try {
            if (film.getFilmCode() == null){
                return response.error("Harus Memiliki Film Code",Config.ERROR_401);
            }

            Film doSave = filmRepo.save(film);
            return response.sukses(doSave);
        }catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Film film) {
        try {
            if (film.getFilmCode() == null){
                return response.error("Wajib diisi",Config.ERROR_401);
            }

            if (filmRepo.getById(film.getId()) == null){
                return  response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            Film newFilm = filmRepo.getById(film.getId());
            newFilm.setFilmCode(film.getFilmCode());
            newFilm.setFilmName(film.getFilmName());
            newFilm.setOnShow(film.getOnShow());

            Film doUpdate = filmRepo.save(newFilm);
            return response.sukses(doUpdate);
        }catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map delete(Film film) {
        try{
            if (film.getId()==null){
                return response.error("Wajib diisi", Config.ERROR_401);
            }

            if (filmRepo.getById(film.getId()) == null){
                return  response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            return response.sukses(filmRepo.save(film));
        }catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror delete: " + e.getMessage(), Config.ERROR_500);
        }

    }


    @Override
    public Map getOnShowFilm(int size, int page) {
        try{

            Pageable show_data = PageRequest.of(page, size);
            Page<Film> list = filmRepo.getOnShowFilm(show_data);

            return response.sukses(list);
        }catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror delete: " + e.getMessage(), Config.ERROR_500);
        }
    }


}
