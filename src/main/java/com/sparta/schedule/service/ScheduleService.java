package com.sparta.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.requestdto.UpdateRequestDto;
import com.sparta.schedule.dto.responsedto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.exception.NotFoundException;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, User user) {
		User persistentUser = userRepository.findById(user.getId())
			.orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
		Schedule schedule = new Schedule(requestDto, persistentUser);
		Schedule savedSchedule = scheduleRepository.save(schedule);
		log.info("스케줄 저장: {}", savedSchedule);
		return new ScheduleResponseDto(savedSchedule);
	}

	@Transactional
	public ScheduleResponseDto updateSchedule(Long id, UpdateRequestDto requestDto, User user) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));

		if (isNotAuthorizedUser(schedule, user)) {
			throw new IllegalArgumentException("권한이 없습니다.");
		}

		schedule.update(requestDto);
		Schedule updatedSchedule = scheduleRepository.save(schedule);
		log.info("스케줄 수정: {}", updatedSchedule);
		return new ScheduleResponseDto(updatedSchedule);
	}

	@Transactional
	public void deleteSchedule(Long id, User user) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));

		if (isNotAuthorizedUser(schedule, user)) {
			throw new IllegalArgumentException("권한이 없습니다.");
		}

		scheduleRepository.delete(schedule);
		log.info("스케줄 삭제: {}", schedule);
	}

	@Transactional(readOnly = true)
	public ScheduleResponseDto getSchedule(Long id) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));
		return new ScheduleResponseDto(schedule);
	}

	@Transactional(readOnly = true)
	public List<ScheduleResponseDto> getAllSchedules() {
		return scheduleRepository.findAll().stream()
			.map(ScheduleResponseDto::new)
			.collect(Collectors.toList());
	}

	private boolean isNotAuthorizedUser(Schedule schedule, User user) {
		User persistentUser = userRepository.findById(user.getId())
			.orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
		return !schedule.getUser().equals(persistentUser) && !persistentUser.isAdmin();
	}

	@Transactional(readOnly = true)
	public Schedule getScheduleById(Long id) {
		return scheduleRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));
	}
}
