package com.challenge4.FilmApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer")

public class Customer {

    @GeneratedValue
    @Id
    private long id;
    private String username;
    private String emailAddress;
    private String password;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "seats")
    private List<Seat> seats;


}
