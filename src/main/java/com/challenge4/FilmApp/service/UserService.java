package com.challenge4.FilmApp.service;

import com.challenge4.FilmApp.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Map;

public interface UserService {
    public Map save(Customer customer);

    public Map update(Customer customer);

    public Map delete(Customer customer);

    Map reserve(String username, String scheduleID, Long seatId);
}
