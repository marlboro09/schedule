package com.sparta.schedule.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.UpdateRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

	@Column(name = "createtime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime time;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	public Schedule(ScheduleRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.name = requestDto.getName();
		this.password = requestDto.getPassword();
		this.date = requestDto.getDate();
		this.time = LocalDateTime.now();
	}

	public void update(UpdateRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.name = requestDto.getName();
		this.password = requestDto.getPassword();
		this.date = requestDto.getDate();
		this.time = LocalDateTime.now();
	}

}
