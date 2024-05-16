package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
	private Long id;
	private String title;
	private String contents;
	private String name;
	private String password;
	private String date;
}
