package com.challenge4.FilmApp.service;

import com.challenge4.FilmApp.entity.Customer;

import java.util.Map;

public interface CustomerService {
    public Map save(Customer customer);

    public Map update(Customer customer);

    public Map delete(Customer customer);

    public Map reserve(String username, String scheduleID,Long filmId ,Long seatId);
}
