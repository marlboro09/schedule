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

	// 매개변수를 받는 생성자
	public ScheduleResponseDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.contents = schedule.getContents();
		this.name = schedule.getName();
		this.date = schedule.getDate();
	}
}
