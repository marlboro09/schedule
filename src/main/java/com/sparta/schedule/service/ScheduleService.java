package com.sparta.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.responsedto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
		Schedule schedule = new Schedule();
		schedule.setTitle(requestDto.getTitle());
		schedule.setContent(requestDto.getContent());
		schedule.setManager(requestDto.getManager());
		schedule.setPassword(requestDto.getPassword());
		schedule.setCreatedDate(requestDto.getCreatedDate());

		Schedule savedSchedule = scheduleRepository.save(schedule);

		ScheduleResponseDto responseDto = new ScheduleResponseDto();
		responseDto.setId(savedSchedule.getId());
		responseDto.setTitle(savedSchedule.getTitle());
		responseDto.setContent(savedSchedule.getContent());
		responseDto.setManager(savedSchedule.getManager());
		responseDto.setCreatedDate(savedSchedule.getCreatedDate());

		return responseDto;
	}

	public ScheduleResponseDto getSchedule(Long id) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Schedule not found"));

		ScheduleResponseDto responseDto = new ScheduleResponseDto();
		responseDto.setId(schedule.getId());
		responseDto.setTitle(schedule.getTitle());
		responseDto.setContent(schedule.getContent());
		responseDto.setManager(schedule.getManager());
		responseDto.setCreatedDate(schedule.getCreatedDate());

		return responseDto;
	}

	public List<ScheduleResponseDto> getAllSchedules() {
		List<Schedule> schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

		return schedules.stream().map(schedule -> {
			ScheduleResponseDto responseDto = new ScheduleResponseDto();
			responseDto.setId(schedule.getId());
			responseDto.setTitle(schedule.getTitle());
			responseDto.setContent(schedule.getContent());
			responseDto.setManager(schedule.getManager());
			responseDto.setCreatedDate(schedule.getCreatedDate());
			return responseDto;
		}).collect(Collectors.toList());
	}

	public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Schedule not found"));

		if (!schedule.getPassword().equals(requestDto.getPassword())) {
			throw new RuntimeException("Password does not match");
		}

		schedule.setTitle(requestDto.getTitle());
		schedule.setContent(requestDto.getContent());
		schedule.setManager(requestDto.getManager());

		Schedule updatedSchedule = scheduleRepository.save(schedule);

		ScheduleResponseDto responseDto = new ScheduleResponseDto();
		responseDto.setId(updatedSchedule.getId());
		responseDto.setTitle(updatedSchedule.getTitle());
		responseDto.setContent(updatedSchedule.getContent());
		responseDto.setManager(updatedSchedule.getManager());
		responseDto.setCreatedDate(updatedSchedule.getCreatedDate());

		return responseDto;
	}

	public void deleteSchedule(Long id, String password) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Schedule not found"));

		if (!schedule.getPassword().equals(password)) {
			throw new RuntimeException("Password does not match");
		}

		scheduleRepository.delete(schedule);
	}
}