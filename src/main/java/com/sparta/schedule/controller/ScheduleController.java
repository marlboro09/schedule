package com.sparta.schedule.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "스케줄 관리", description = "스케줄을 등록, 조회, 수정, 삭제하실 수 있습니다")
@RestController
@RequestMapping("/api")
public class ScheduleController {
	private final ScheduleService scheduleService;
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	/**
	 * 스케줄 등록
	 */
	@PostMapping("/schedule")
	public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
		return scheduleService.createSchedule(requestDto);
	}
	/**
	 * 전체 스케줄 조회
	 */
	@GetMapping("/schedule/list")
	public List<ScheduleResponseDto> getSchedules() {
		return scheduleService.getSchedules();
	}

	/**
	 * 선택 스케줄 조회
	 */
	@GetMapping("/schedule/{id}")
	public ScheduleResponseDto getScheduleById(@PathVariable Long id) {
		return scheduleService.getScheduleById(id);
	}
	/**
	 * 스케줄 수정
	 */
	@PutMapping("/schedule/{id}")
	public ScheduleResponseDto updateSchedule(
		@PathVariable Long id,
		@RequestParam String password,
		@RequestBody ScheduleRequestDto requestDto) {
		return scheduleService.updateSchedule(id, password, requestDto);
	}
	/**
     * 스케줄 삭제
     */
	@DeleteMapping("/schedule/{id}")
    public long deleteSchedule(
		@PathVariable Long id,
		@RequestParam String password) {
		return scheduleService.deleteSchedule(id, password);
	}
}
