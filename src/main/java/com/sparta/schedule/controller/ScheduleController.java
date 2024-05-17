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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
	@Operation(
		summary = "스케줄 등록",
		description = "스케줄을 등록합니다",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "스케줄 입력이 완료되었습니다."
			), @ApiResponse(
				responseCode = "400",
				description = "스케줄 입력에 실패했습니다. 입력값을 확인해주세요."
			), @ApiResponse(
				responseCode = "500",
				description = "서버 오류가 발생했습니다. 다시 시도하세요."
			)
		}
	)
	@PostMapping("/schedule")
	public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
		return scheduleService.createSchedule(requestDto);
	}
	/**
	 * 전체 스케줄 조회
	 */@Operation(
		summary = "스케줄 전체 조회",
		description = "등록된 모든 스케줄 목록입니다",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "모든 스케줄이 호출되었습니다"
			), @ApiResponse(
				responseCode = "500",
				description = "서버 오류가 발생했습니다. 다시 시도하세요."
			)
		}
	)
	@GetMapping("/schedule/list")
	public List<ScheduleResponseDto> getSchedules() {
		return scheduleService.getSchedules();
	}

	/**
	 * 선택 스케줄 조회
	 */
	@Operation(
		summary = "선택한 스케줄 조회",
		description = "원하는 스케줄의 id를 받아 선택한 스케줄을 조회합니다",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "선택한 스케줄이 호출되었습니다."
			), @ApiResponse(
				responseCode = "400",
				description = "잘못된 요청입니다. id를 확인해주세요."
			), @ApiResponse(
				responseCode = "404",
				description = "선택한 스케줄 정보를 찾을 수 없습니다."
			), @ApiResponse(
				responseCode = "500",
				description = "서버 오류가 발생했습니다. 다시 시도하세요."
			)
		}
	)
	@GetMapping("/schedule/{id}")
	public ScheduleResponseDto getScheduleById(@PathVariable Long id) {
		return scheduleService.getScheduleById(id);
	}
	/**
	 * 스케줄 수정
	 */
	@Operation(
		summary = "스케줄 수정",
		description = "id값과 pw를 입력받아 선택한 스케줄 수정합니다",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "스케줄 수정이 완료되었습니다"
			), @ApiResponse(
			responseCode = "400",
			description = "잘못된 요청입니다. id와 password를 확인해주세요."
			), @ApiResponse(
			responseCode = "401",
			description = "비밀번호가 일치하지 않습니다."
			), @ApiResponse(
			responseCode = "404",
			description = "선택한 스케줄 정보를 찾을 수 없습니다."
			), @ApiResponse(
				responseCode = "500",
				description = "서버 오류가 발생했습니다. 다시 시도하세요."
			)
		}
	)
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
	@Operation(
		summary = "스케줄 삭제",
		description = "id값과 pw를 입력받아 선택한 스케줄을 삭제할 수 있습니다",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "스케줄이 삭제되었습니다"
			), @ApiResponse(
			responseCode = "400",
			description = "잘못된 요청입니다. id와 password를 확인해주세요."
			), @ApiResponse(
				responseCode = "401",
				description = "비밀번호가 일치하지 않습니다."
			), @ApiResponse(
			responseCode = "403",
			description = "접근 권한이 없습니다."
			), @ApiResponse(
				responseCode = "404",
				description = "선택한 스케줄 정보를 찾을 수 없습니다."
			), @ApiResponse(
				responseCode = "409",
				description = "이미 삭제된 스케줄입니다."
			), @ApiResponse(
				responseCode = "500",
				description = "서버 오류가 발생했습니다. 다시 시도하세요."
			)
		}
	)
	@DeleteMapping("/schedule/{id}")
    public long deleteSchedule(
		@PathVariable Long id,
		@RequestParam String password) {
		return scheduleService.deleteSchedule(id, password);
	}
}
