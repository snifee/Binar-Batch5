package com.challenge4.FilmApp.service;

import com.challenge4.FilmApp.entity.Schedule;

import java.util.Map;

public interface ScheduleService {
    public Map save(Schedule schedule);

    public Map update(Schedule schedule);

    public Map delete(Schedule schedule);

    public Map getById(Integer id);
}
