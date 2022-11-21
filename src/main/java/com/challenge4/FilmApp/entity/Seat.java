package com.challenge4.FilmApp.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.List;


@Table(name = "Seat")
@Data
@Entity

//create table Seats (studio varchar(100), seat_number int, primary KEY(studio,seat_number));

public class Seat {

    @GeneratedValue
    @Id
    private Long id;

    private String namaStudio;
    private String noKursi;

    @OneToMany
    @JoinColumn(name = "seat_id")
    private List<SchedulingFilm> schedulingFilms;
}
