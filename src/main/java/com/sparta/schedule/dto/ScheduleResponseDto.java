package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {
	private Long id;
	private String title;
	private String contents;
	private String name;
	private String date;

	public ScheduleResponseDto(Schedule schedule) {
		this.id = schedule.getId();
		this.name = schedule.getName();
		this.title = schedule.getTitle();
		this.contents = schedule.getContents();
		this.date = schedule.getDate();
	}
}
