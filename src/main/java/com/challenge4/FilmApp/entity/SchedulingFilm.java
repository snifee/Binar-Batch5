package com.challenge4.FilmApp.entity;

import com.challenge4.FilmApp.entity.Film;
import com.challenge4.FilmApp.entity.Schedule;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "SchedulingFilm")
@Data

public class SchedulingFilm {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "filmId")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;
}
