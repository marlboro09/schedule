package com.sparta.schedule.controller;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;

@RestController
@RequestMapping("/api")
public class ScheduleController {
	private final JdbcTemplate jdbcTemplate;

	public ScheduleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 스케줄 등록
	 */
	@PostMapping("/schedule")
	public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
		ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
		return scheduleService.createSchedule(requestDto);
	}
	/**
	 * 전체 스케줄 조회
	 */
	@GetMapping("/schedule/list")
	public List<ScheduleResponseDto> getSchedules() {
		ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
		return scheduleService.getSchedules();
	}
	/**
	 * 선택 스케줄 조회
	 */
	@GetMapping("/schedule/list/{id}")
	public ScheduleResponseDto getScheduleById(@PathVariable Long id,@RequestBody ScheduleRequestDto requestDto) {
		ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
		return scheduleService.checkSchedule(id, requestDto);
	}
	/**
	 * 스케줄 수정
	 */
	@PutMapping("/schedule/{id}")
	public long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
		ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
		return scheduleService.updateSchedule(id, requestDto);


	}
	/**
     * 스케줄 삭제
     */
	@DeleteMapping("/schedule/{id}")
    public long deleteSchedule(@PathVariable Long id) {
		ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
		return scheduleService.deleteSchedule(id);
	}


}
