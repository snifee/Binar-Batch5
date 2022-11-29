package com.challenge4.FilmApp.repository;

import com.challenge4.FilmApp.entity.SchedulingFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SFRepo extends JpaRepository<SchedulingFilm,String> {


    @Query(value = "insert into SchedulingFilm(id,user_id,film_id,schedule_id,seat_id) values(:id,:userId,:filmId,:scheduleId,:seatId)", nativeQuery = true)
    public void saveReserve(@Param("id")Long id,
                            @Param("userId") Long userId,
                            @Param("filmId") Long filmId,
                            @Param("scheduleId") String scheduleId,
                            @Param("seatId") Long seatId);
}
