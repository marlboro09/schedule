package com.sparta.schedule.dto.responsedto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CommentResponseDto {

	private Long id;

	private String content;

	private String userId;

	private Long scheduleId;

	private LocalDateTime createdDate;
}
