package com.sparta.schedule.dto.responsedto;

import java.time.LocalDateTime;

import com.sparta.schedule.entity.Schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
	private Long id;

	private String title;

	private String description;

	private String nickname;

	private String date;

	private LocalDateTime createdAt;

	public ScheduleResponseDto(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.description = schedule.getDescription();
		this.nickname = schedule.getUser().getNickname();
		this.date = schedule.getDate();
		this.createdAt = schedule.getCreatedAt();
	}
}