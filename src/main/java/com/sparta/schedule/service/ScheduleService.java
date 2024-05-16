package com.sparta.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
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

		Schedule saveSchedule = scheduleRepository.save(schedule);

		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

		return scheduleResponseDto;
	}

	public List<ScheduleResponseDto> getSchedules() {
		//db조회
		return scheduleRepository.findAllByOrderByDateDesc().stream().map(ScheduleResponseDto::new).toList();
	}
	@Transactional
	public long updateSchedule(Long id, ScheduleRequestDto requestDto) {
		Schedule schedule = findSchedule(id);
		schedule.update(requestDto);
		return id;
	}

	public long deleteSchedule(Long id) {
		Schedule schedule = findSchedule(id);
		scheduleRepository.delete(schedule);
		return id;
	}

	private Schedule findSchedule(Long id) {
		return scheduleRepository.findById(id).orElseThrow(()->
			new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다")
		);
	}
}
