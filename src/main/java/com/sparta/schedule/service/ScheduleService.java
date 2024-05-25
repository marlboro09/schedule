package com.sparta.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.dto.UpdateRequestDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;

@Service
public class ScheduleService {
	private final ScheduleRepository scheduleRepository;

	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	/**
	 * 스케줄 등록
	 */
	public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
		Schedule schedule = new Schedule(requestDto);
		Schedule savedSchedule = scheduleRepository.save(schedule);
		return new ScheduleResponseDto(savedSchedule);
	}

	public List<ScheduleResponseDto> getSchedules() {
		// DB 조회
		return scheduleRepository.findAllByOrderByDateDesc().stream()
			.map(ScheduleResponseDto::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ScheduleResponseDto getScheduleById(Long id) {
		Schedule schedule = findSchedule(id);
		return new ScheduleResponseDto(schedule);
	}

	@Transactional
	public ScheduleResponseDto updateSchedule(Long id, String password, UpdateRequestDto requestDto) {
		// 비밀번호 확인
		if (!isValidPassword(id, password)) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		// 일정 업데이트
		Schedule schedule = findSchedule(id);
		schedule.update(requestDto);
		return new ScheduleResponseDto(schedule);
	}

	@Transactional
	public void deleteSchedule(Long id, String password) {
		// 비밀번호 확인
		if (!isValidPassword(id, password)) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		Schedule schedule = findSchedule(id);
		scheduleRepository.delete(schedule);
	}

	@Transactional(readOnly = true)
	private Schedule findSchedule(Long id) {
		return scheduleRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다"));
	}

	private boolean isValidPassword(Long id, String password) {
		Schedule schedule = findSchedule(id);
		return schedule.getPassword().equals(password);
	}
}
