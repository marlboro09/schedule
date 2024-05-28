package com.sparta.schedule.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.schedule.dto.CommentRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String contents;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private User user;

	public Comment(CommentRequestDto requestDto, Schedule schedule) {
		this.contents = requestDto.getContents();
		this.name = requestDto.getName();
		this.schedule = schedule;
		this.time = LocalDateTime.now();
	}

	public void setUser(User user) {
		this.user = user;
	}
}
