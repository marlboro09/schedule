package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "Schedule 내용 DTO")
public class ScheduleResponseDto {
	@Schema(description = "id입니다")
	private Long id;
	@Schema(description = "제목입니다")
	private String title;
	@Schema(description = "스케줄 내용입니다")
	private String contents;
	@Schema(description = "작성자입니다")
	private String name;
	@Schema(description = "스케줄 날짜입니다")
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
