package com.challenge4.FilmApp.repository;

import com.challenge4.FilmApp.entity.Film;
import com.challenge4.FilmApp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepo extends PagingAndSortingRepository<Schedule,String> {
    @Query(value = "select s from Schedule s where s.id = :id", nativeQuery = true)
    Schedule getScheduleById(@Param("id")String id);
}
