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
	private String contents;
	private String name;
	private String password;
	private String date;

	public Schedule(ScheduleRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.name = requestDto.getName();
		this.password = requestDto.getPassword();
		this.date = requestDto.getDate();
	}

	public void update(ScheduleRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.name = requestDto.getName();
		this.password = requestDto.getPassword();
		this.date = requestDto.getDate();
	}
}
