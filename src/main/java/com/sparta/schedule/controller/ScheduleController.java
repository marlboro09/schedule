package com.sparta.schedule.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {
	private final Map<Long, Schedule> scheduleList = new HashMap<>();

	@PostMapping("/schedule")
	public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
		Schedule schedule = new Schedule(requestDto);

		Long maxId = scheduleList.size() > 0 ? Collections.max((scheduleList.keySet()) + 1 : 1;
		schedule.setId(maxId);

		scheduleList.put(schedule.getId(), schedule);

		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

		return scheduleResponseDto;
	}

	@GetMapping("/schedule")
	public List<ScheduleResponseDto> getSchedules() {
		List<ScheduleResponseDto> responseList = scheduleList.values().stream()
			.map(ScheduleResponseDto::new).toList();

		return responseList
	}


}
