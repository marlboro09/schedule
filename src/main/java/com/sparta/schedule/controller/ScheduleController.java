package com.sparta.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.UpdateRequestDto;
import com.sparta.schedule.dto.PasswordRequestDto;
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

	@PostMapping("/schedule")
	public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
		return scheduleService.createSchedule(requestDto);
	}

	@GetMapping("/schedule/list")
	public List<ScheduleResponseDto> getSchedules() {
		return scheduleService.getSchedules();
	}

	@GetMapping("/schedule/{id}")
	public ScheduleResponseDto getScheduleById(@PathVariable(name = "id") Long id) {
		return scheduleService.getScheduleById(id);
	}

	@PutMapping("/schedule/update")
	public ScheduleResponseDto updateSchedule(@RequestBody UpdateRequestDto requestDto) {
		return scheduleService.updateSchedule(requestDto.getId(), requestDto.getPassword(), requestDto);
	}

	@DeleteMapping("/schedule")
	public ResponseEntity<Void> deleteSchedule(
		@RequestBody PasswordRequestDto requestDto) {
		scheduleService.deleteSchedule(requestDto.getId(), requestDto.getPassword());
		return ResponseEntity.noContent().build();
	}
}
