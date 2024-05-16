package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {
	private Long id;
	private String title;
	private String contents;
	private String name;
	private String password;
	private String date;

	public ScheduleResponseDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.contents = schedule.getContents();
		this.name = schedule.getName();
		this.password = schedule.getPassword();
		this.date = schedule.getDate();
	}
}
