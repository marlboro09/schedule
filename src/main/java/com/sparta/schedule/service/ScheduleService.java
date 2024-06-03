package com.sparta.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.requestdto.UpdateRequestDto;
import com.sparta.schedule.dto.responsedto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.exception.NotFoundException;
import com.sparta.schedule.repository.ScheduleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, User user) {
		Schedule schedule = new Schedule(requestDto, user);
		try {
			Schedule savedSchedule = scheduleRepository.save(schedule);
			return new ScheduleResponseDto(savedSchedule);
		} catch (Exception e) {
			log.error("스케줄 생성 중 오류 발생: ", e);
			throw new RuntimeException("스케줄 생성에 실패했습니다.");
		}
	}

	public ScheduleResponseDto getSchedule(Long id) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));
		return new ScheduleResponseDto(schedule);
	}

	public List<ScheduleResponseDto> getAllSchedules() {
		return scheduleRepository.findAllByOrderByDateDesc().stream()
			.map(ScheduleResponseDto::new)
			.collect(Collectors.toList());
	}

	public ScheduleResponseDto updateSchedule(Long id, UpdateRequestDto requestDto, User user) {
		log.debug("스케줄 수정 ID: {}", id);
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));

		if (!schedule.getUser().getId().equals(user.getId()) && !user.isAdmin()) {
			log.error("스케줄을 수정할 권한이 없습니다.", user.getUsername(), id);
			throw new IllegalArgumentException("스케줄 수정 권한이 없습니다.");
		}

		schedule.update(requestDto);
		try {
			return new ScheduleResponseDto(schedule);
		} catch (Exception e) {
			log.error("스케줄 수정 중 오류 발생: ", e);
			throw new RuntimeException("스케줄 수정에 실패했습니다.");
		}
	}

	public void deleteSchedule(Long id, User user) {
		log.debug("스케줄 삭제 중: ID: {}", id);
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));

		if (!schedule.getUser().getId().equals(user.getId()) && !user.isAdmin()) {
			log.error("스케줄을 삭제할 권한이 없습니다.", user.getUsername(), id);
			throw new IllegalArgumentException("스케줄 삭제 권한이 없습니다.");
		}

		try {
			scheduleRepository.delete(schedule);
		} catch (Exception e) {
			log.error("스케줄 삭제 중 오류 발생: ", e);
			throw new RuntimeException("스케줄 삭제에 실패했습니다.");
		}
	}
}