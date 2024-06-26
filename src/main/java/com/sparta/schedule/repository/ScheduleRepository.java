package com.sparta.schedule.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	Collection<Schedule> findAllByOrderByDateDesc();
}
