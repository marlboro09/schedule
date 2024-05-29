package com.sparta.schedule.dto.responsedto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ScheduleResponseDto {
	private Long id;

	private String title;

	private String content;

	private String manager;

	private LocalDateTime createdDate;
}
