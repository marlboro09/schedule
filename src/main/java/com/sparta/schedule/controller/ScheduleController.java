package com.sparta.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.responsedto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "스케줄 관리", description = "스케줄 관리 API입니다")
@RestController
@RequestMapping("/api")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/schedules")
	public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
		ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/schedules/{id}")
	public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
		ScheduleResponseDto responseDto = scheduleService.getSchedule(id);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/schedules/list")
	public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
		List<ScheduleResponseDto> responseDtos = scheduleService.getAllSchedules();
		return ResponseEntity.ok(responseDtos);
	}

	@PutMapping("/schedules/{id}")
	public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto) {
		ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/schedules/{id}")
	public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestParam String password) {
		scheduleService.deleteSchedule(id, password);
		return ResponseEntity.noContent().build();
	}
}