package com.sparta.schedule.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
	private Long id;
	private String name;
	private String password;
	private String date;
	private String contents;
}
