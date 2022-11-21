package com.challenge4.FilmApp.controller;

import com.challenge4.FilmApp.entity.Customer;
import com.challenge4.FilmApp.entity.Film;
import com.challenge4.FilmApp.repository.FilmRepo;
import com.challenge4.FilmApp.repository.UserRepo;
import com.challenge4.FilmApp.service.UserService;
import com.challenge4.FilmApp.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/binar/user")
public class UserController {
    @Autowired
    public Response response;

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public UserService userService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Customer customer){
        return new ResponseEntity<Map>(userService.save(customer), HttpStatus.OK);
    }


    @PutMapping(value = {"/update","/update/"})
    public ResponseEntity<Map> update(@RequestBody Customer customer){
        return new ResponseEntity<Map>(userService.update(customer),HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete","/delete/"})
    public ResponseEntity<Map> delete(@RequestBody Customer customer){
        return new ResponseEntity<Map>(userService.delete(customer),HttpStatus.OK);
    }

    @PostMapping(value = {"/reserve"})
    public ResponseEntity<Map> reserve( @RequestParam("username") String username,@RequestParam("scheduleId") String scheduleId,@RequestParam("seatId") Long seatId){
        return new ResponseEntity<Map>(userService.reserve(username,scheduleId,seatId),HttpStatus.OK);
    }

//    public Map reserve(String username, String filmCode, Long scheduleID, Long seatId){
}
