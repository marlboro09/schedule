package com.sparta.schedule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.requestdto.UpdateRequestDto;
import com.sparta.schedule.dto.responsedto.ScheduleResponseDto;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.ScheduleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Tag(name = "스케줄 관리", description = "스케줄 관리 API입니다")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;

	@PostMapping("/schedule")
	public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto, userDetails.getUser());
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/schedule/{id}")
	public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
		ScheduleResponseDto responseDto = scheduleService.getSchedule(id);
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/schedule/list")
	public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
		List<ScheduleResponseDto> responseDtos = scheduleService.getAllSchedules();
		return ResponseEntity.ok(responseDtos);
	}

	@PutMapping("/schedule/{id}")
	public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody UpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto, userDetails.getUser());
		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		scheduleService.deleteSchedule(id, userDetails.getUser());
		return ResponseEntity.noContent().build();
	}
}
