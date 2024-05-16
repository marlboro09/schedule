package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "contents", nullable = false)
	private String contents;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "date", nullable = false)
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
