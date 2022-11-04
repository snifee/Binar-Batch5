package com.challenge4.FilmApp.controller;


import com.challenge4.FilmApp.entity.Film;
import com.challenge4.FilmApp.repository.FilmRepo;
import com.challenge4.FilmApp.service.FilmService;
import com.challenge4.FilmApp.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/binar/film")
public class FilmController {
    @Autowired
    public Response response;

    @Autowired
    public FilmRepo filmRepo;

    @Autowired
    public FilmService filmService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Film film){
        return new ResponseEntity<Map>(filmService.save(film), HttpStatus.OK);
    }


    @PutMapping(value = {"/update","/update/"})
    public ResponseEntity<Map> update(@RequestBody Film film){
        return new ResponseEntity<Map>(filmService.update(film),HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete","/delete/"})
    public ResponseEntity<Map> delete(@RequestBody Film film){
        return new ResponseEntity<Map>(filmService.delete(film),HttpStatus.OK);
    }

    @GetMapping(value = {"/onshow","/onshow/"})
    public ResponseEntity<Map> onshow(
            @RequestParam() Integer page,
            @RequestParam() Integer size){

        Map list = filmService.getOnShowFilm(size, page);

        return new ResponseEntity<Map>(list,HttpStatus.OK);
    }

//    @GetMapping("/list")
//    public ResponseEntity<Map> listByBama(
//            @RequestParam() Integer page,
//            @RequestParam() Integer size) {
//        Map list = barangService.getAll(size, page);
//        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);
//    }

}
