package com.challenge4.FilmApp.repository;

import com.challenge4.FilmApp.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends PagingAndSortingRepository<Customer,Long> {

    @Query(value = "select c from Customer c where c.id = :id")
    public Object getUserById(@Param("id") Long id);

}
