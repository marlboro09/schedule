package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
	private Long id;
	private String title;
	private String name;
	private String password;
	private String date;
	private String contents;

	public Schedule(ScheduleRequestDto requestDto) {
		this.name = requestDto.getName();
		this.contents = requestDto.getContents();
	}
}
