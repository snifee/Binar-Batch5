package com.challenge4.FilmApp.entity;

import javax.persistence.*;

import lombok.Data;


@Table(name = "Seat")
@Data
@Entity

//create table Seats (studio varchar(100), seat_number int, primary KEY(studio,seat_number));

public class Seat {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "schedulingFilmId")
    private SchedulingFilm schedulingFilm;

    private String namaStudio;
    private String noKursi;

}
