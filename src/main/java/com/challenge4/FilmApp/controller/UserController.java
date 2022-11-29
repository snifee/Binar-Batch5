package com.challenge4.FilmApp.controller;

import com.challenge4.FilmApp.entity.Customer;
import com.challenge4.FilmApp.repository.UserRepo;
import com.challenge4.FilmApp.service.CustomerService;
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
    public CustomerService customerService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Customer customer){
        return new ResponseEntity<Map>(customerService.save(customer), HttpStatus.OK);
    }


    @PutMapping(value = {"/update","/update/"})
    public ResponseEntity<Map> update(@RequestBody Customer customer){
        return new ResponseEntity<Map>(customerService.update(customer),HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete","/delete/"})
    public ResponseEntity<Map> delete(@RequestBody Customer customer){
        return new ResponseEntity<Map>(customerService.delete(customer),HttpStatus.OK);
    }

    @PostMapping(value = {"/reserve"})
    public ResponseEntity<Map> reserve( @RequestParam("username") String username,@RequestParam("scheduleId") String scheduleId,@RequestParam("seatId") Long seatId,@RequestParam("filmId") Long filmId){
        return new ResponseEntity<Map>(customerService.reserve(username,scheduleId,filmId,seatId),HttpStatus.OK);
    }
//    public Map reserve(String username, String scheduleID,Long filmId ,Long seatId);
//    public Map reserve(String username, String filmCode, Long scheduleID, Long seatId){
}
