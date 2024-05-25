package com.sparta.schedule.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.repository.ScheduleRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createtime;


	public Comment(CommentRequestDto requestDto, ScheduleRepository scheduleRepository) {
		this.contents = requestDto.getContents();
		this.name = requestDto.getName();
		Long scheduleId = requestDto.getScheduleId();
		Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
		this.schedule = schedule;
		this.createtime = LocalDateTime.now(); // 현재 시간으로 설정
	}


}
