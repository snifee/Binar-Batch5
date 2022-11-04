package com.challenge4.FilmApp.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

//create table Schedules(schedule_id int primary key GENERATED ALWAYS AS IDENTITY,
//        film_code varchar(100),
//        show_time DATE,
//        jam_mulai TIME,
//        jam_selesai TIME,
//        harga_tiket int,
//        constraint fk_films
//        foreign key(film_code)
//        references Films(film_code));

@Data
@Entity
@Table(name = "Schedules")
public class Schedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yy")
    @Column(name = "jadwalTayang")
    private LocalDate jadwalTayang;

    @Column(name = "jamMulai")
    private LocalTime jamMulai;

    @Column(name = "jamSelesai")
    private LocalTime jamSelesai;

    @Column(name = "hargaTiket")
    private Integer hargaTiket;




}
