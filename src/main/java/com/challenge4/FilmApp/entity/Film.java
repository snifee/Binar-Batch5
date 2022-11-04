package com.challenge4.FilmApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "films")
public class Film{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "filmCode", length = 100)
    private String filmCode;

    @Column(name = "filmName")
    private String filmName;

    @Column(name = "onShow")
    private Boolean onShow;

}
