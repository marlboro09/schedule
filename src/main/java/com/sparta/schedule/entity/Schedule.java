package com.sparta.schedule.entity;

import java.time.LocalDateTime;

import com.sparta.schedule.dto.requestdto.ScheduleRequestDto;
import com.sparta.schedule.dto.requestdto.UpdateRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String date;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private LocalDateTime createdAt = LocalDateTime.now();

	public Schedule(ScheduleRequestDto requestDto, User user) {
		this.title = requestDto.getTitle();
		this.description = requestDto.getDescription();
		this.date = requestDto.getDate();
		this.user = user;
		this.createdAt = LocalDateTime.now();
	}

	public void update(UpdateRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.description = requestDto.getDescription();
		this.date = requestDto.getDate();
	}
}